package com.kepai.common.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kepai.common.config.exception.ApiException;
import com.kepai.common.mapper.AuthRoleMapper;
import com.kepai.common.pojos.dao.AuthRole;
import com.kepai.common.pojos.dto.RoleDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthRoleService extends ServiceImpl<AuthRoleMapper, AuthRole> {


    /**
     * 保存或更新
     *
     * @param dto
     */
    public void saveOrUpdate(RoleDTO dto) {
        AuthRole authModule = BeanUtil.copyProperties(dto, AuthRole.class);
        if (authModule.getId() == null) {
            // 插入新数据
            authModule.setCreateTime(DateUtil.date());
        }
        authModule.setUpdateTime(DateUtil.date());
        saveOrUpdate(authModule);
    }


    /**
     * 改变模块状态
     */
    public void changeState(String id) {
        AuthRole authModule = getById(id);
        if (authModule == null) {
            throw new ApiException("角色不存在");
        }
        authModule.setIsDeleted(1 - authModule.getIsDeleted());
        updateById(authModule);
    }


}
