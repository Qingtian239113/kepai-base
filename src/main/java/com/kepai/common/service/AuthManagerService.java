package com.kepai.common.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kepai.common.config.exception.ApiException;
import com.kepai.common.mapper.AuthManagerMapper;
import com.kepai.common.pojos.dao.AuthManager;
import com.kepai.common.pojos.dto.AuthUserDTO;
import com.kepai.common.pojos.dto.UserLoginDTO;
import com.kepai.common.pojos.vo.LoginVo;
import com.kepai.common.utils.PasswordUtils;
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
    public LoginVo userLogin(UserLoginDTO dto, HttpServletRequest request) {
        QueryWrapper<AuthManager> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", dto.getAccount());
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.last("limit 1");
        AuthManager authManager = getOne(queryWrapper);

        if (authManager == null) {
            throw new ApiException("该账号不存在");
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
        List<Map<String, Object>> mapList = getBaseMapper().getModuleOneByRoleGroup(authManager.getRoleId());
        if (authManager.getIsSuper() != 0) {
            // 模块管理
            Map<String, Object> moduleMap = new HashMap<>(16);
            moduleMap.put("id", "module");
            moduleMap.put("name", "模块管理");
            mapList.add(moduleMap);

            // 管理人员
            Map<String, Object> authMap = new HashMap<>(16);
            authMap.put("id", "auth");
            authMap.put("name", "管理人员");
            mapList.add(authMap);

            // 系统配置
            Map<String, Object> systemMap = new HashMap<>(16);
            systemMap.put("id", "system");
            systemMap.put("name", "系统管理");
            mapList.add(systemMap);
        }

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
