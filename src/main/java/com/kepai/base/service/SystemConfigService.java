package com.kepai.base.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kepai.base.mapper.BaseSystemConfigMapper;
import com.kepai.base.pojos.dao.BaseSystemConfig;
import com.kepai.base.pojos.dto.PageDTO;
import org.springframework.stereotype.Service;

/**
 * @author hao
 */
@Service
public class SystemConfigService extends ServiceImpl<BaseSystemConfigMapper, BaseSystemConfig> {


    /**
     * 分页获取系统配置
     *
     * @param dto
     * @return
     */
    public Page<BaseSystemConfig> getListByPage(PageDTO dto) {
        Page<BaseSystemConfig> page = dto.pageObj();
        QueryWrapper<BaseSystemConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        page(page, queryWrapper);
        return page;
    }


    /**
     * 获取一个config
     *
     * @param configKey
     * @return
     */
    public BaseSystemConfig getConfigOne(String configKey, String defaultVal) {
        QueryWrapper<BaseSystemConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.eq("config_key", configKey);
        queryWrapper.last("limit 1");

        BaseSystemConfig config = getOne(queryWrapper);
        if (config == null) {
            config = new BaseSystemConfig();
            config.setConfigVal(defaultVal);
        }
        return config;
    }


}
