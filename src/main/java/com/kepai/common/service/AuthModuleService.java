package com.kepai.common.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kepai.common.config.exception.ApiException;
import com.kepai.common.mapper.AuthModuleMapper;
import com.kepai.common.pojos.dao.AuthModule;
import com.kepai.common.pojos.dto.ModuleDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthModuleService extends ServiceImpl<AuthModuleMapper, AuthModule> {


    /**
     * 保存或更新
     *
     * @param dto
     */
    public void saveOrUpdate(ModuleDTO dto) {
        AuthModule authModule = getById(dto.getId());

        if (authModule == null) {
            // 插入新数据
            authModule = BeanUtil.copyProperties(dto, AuthModule.class);
            authModule.setCreateTime(DateUtil.date());
            authModule.setUpdateTime(DateUtil.date());
            authModule.setSeq(getBaseMapper().getMaxSeq() + 1);
            save(authModule);
        } else {
            // 更新模块
            authModule = BeanUtil.copyProperties(dto, AuthModule.class);
            authModule.setUpdateTime(DateUtil.date());
            updateById(authModule);
        }
    }


    /**
     * 改变模块状态
     */
    public void changeState(String id) {
        AuthModule authModule = getById(id);
        if (authModule == null) {
            throw new ApiException("模块不存在");
        }
        authModule.setIsDeleted(1 - authModule.getIsDeleted());
        updateById(authModule);
    }


}
