package com.kepai.base.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kepai.base.config.exception.ApiException;
import com.kepai.base.mapper.AuthManagerMapper;
import com.kepai.base.pojos.dao.AuthManager;
import com.kepai.base.pojos.dto.AuthUserDTO;
import com.kepai.base.pojos.dto.UserLoginDTO;
import com.kepai.base.pojos.vo.LoginVo;
import com.kepai.base.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthManagerService extends ServiceImpl<AuthManagerMapper, AuthManager> {

    @Autowired
    SystemLogService systemLogService;


    /**
     * 保存或更新
     *
     * @param dto
     */
    public void saveOrUpdate(AuthUserDTO dto) {
        AuthManager authModule = BeanUtil.copyProperties(dto, AuthManager.class);
        if (authModule.getId() == null) {
            // 插入新数据
            authModule.setCreateTime(DateUtil.date());
            if (StrUtil.isEmpty(dto.getPassword())) {
                throw new ApiException("请输入密码");
            }
        }
        if (!StrUtil.isEmpty(dto.getPassword())) {
            authModule.setPassword(PasswordUtils.buildPassword(dto.getPassword()));
        }
        authModule.setUpdateTime(DateUtil.date());
        saveOrUpdate(authModule);
    }


    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    public AuthManager getByUserId(String userId) {
        AuthManager authManager = getById(userId);
        if (authManager == null) {
            throw new ApiException("该管理员不存在");
        }
        return authManager;
    }


    /**
     * 改变模块状态
     */
    public void changeState(String id) {
        AuthManager authModule = getById(id);
        if (authModule == null) {
            throw new ApiException("管理员不存在");
        }
        authModule.setIsDeleted(1 - authModule.getIsDeleted());
        updateById(authModule);
    }


    /**
     * 用户登录
     *
     * @param dto
     */
    public LoginVo userLogin(UserLoginDTO dto, HttpServletRequest request, boolean onlySuper) {
        QueryWrapper<AuthManager> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", dto.getAccount());
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.last("limit 1");
        AuthManager authManager = getOne(queryWrapper);

        if (authManager == null) {
            throw new ApiException("该账号不存在");
        }
        if (onlySuper && authManager.getIsSuper() != 1) {
            throw new ApiException("您无权登录超管端");
        }

        PasswordUtils.verifyPassword(dto.getPassword(), authManager.getPassword());

        // 添加登录日志
        systemLogService.addLoginLog(authManager.getId(), request);

        return LoginVo.parse(authManager);
    }


    /**
     * 根据用户id获取一级模块
     *
     * @param userId
     * @return
     */
    public List<Map<String, Object>> getModuleOneByUserId(String userId) {
        AuthManager authManager = getById(userId);
        if (authManager == null || authManager.getIsDeleted() != 0) {
            throw new ApiException("该账号不存在");
        }

        // 用户拥有的模块
        List<Map<String, Object>> mapList = getBaseMapper().getModuleOneByRoleGroup(authManager.getRoleId());

        // 欢迎页
        Map<String, Object> welcomeMap = new HashMap<>(16);
        welcomeMap.put("id", "welcome");
        welcomeMap.put("name", "欢迎");
        mapList.add(0, welcomeMap);

        return mapList;
    }

    /**
     * 获取二级模块
     *
     * @param userId
     * @return
     */
    public List<Map<String, Object>> getModuleTwoByUserId(String userId, String moduleId) {
        AuthManager authManager = getById(userId);
        if (authManager == null || authManager.getIsDeleted() != 0) {
            throw new ApiException("该账号不存在");
        }
        return getBaseMapper().getModuleTwoByRoleGroup(authManager.getRoleId(), moduleId);
    }


}
