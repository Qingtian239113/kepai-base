package com.kepai.base.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kepai.base.config.exception.ApiException;
import com.kepai.base.mapper.AuthModulesubMapper;
import com.kepai.base.pojos.dao.AuthModulesub;
import com.kepai.base.pojos.dto.ModuleSubDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthModuleSubService extends ServiceImpl<AuthModulesubMapper, AuthModulesub> {


    /**
     * 保存或更新
     *
     * @param dto
     */
    public void saveOrUpdate(ModuleSubDTO dto) {

        // 二级模块的id由1级组合
        String subId = String.format("%s-%s", dto.getModuleId(), dto.getId());

        AuthModulesub authModule = getById(subId);

        if (authModule == null) {
            // 插入新数据
            authModule = BeanUtil.copyProperties(dto, AuthModulesub.class);
            authModule.setCreateTime(DateUtil.date());
            authModule.setUpdateTime(DateUtil.date());
            authModule.setId(subId);
            authModule.setSeq(getBaseMapper().getMaxSeq() + 1);
            save(authModule);
        } else {
            // 更新模块
            authModule = BeanUtil.copyProperties(dto, AuthModulesub.class);
            authModule.setUpdateTime(DateUtil.date());
            authModule.setId(subId);
            updateById(authModule);
        }
    }


    /**
     * 改变模块状态
     */
    public void changeState(String id) {
        AuthModulesub authModule = getById(id);
        if (authModule == null) {
            throw new ApiException("模块不存在");
        }
        authModule.setIsDeleted(1 - authModule.getIsDeleted());
        updateById(authModule);
    }


}
