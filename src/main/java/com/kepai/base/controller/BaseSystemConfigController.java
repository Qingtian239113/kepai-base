package com.kepai.base.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kepai.base.pojos.ApiResp;
import com.kepai.base.pojos.dao.BaseSystemConfig;
import com.kepai.base.pojos.dto.IdDTO;
import com.kepai.base.pojos.dto.PageDTO;
import com.kepai.base.pojos.dto.SystemConfigDTO;
import com.kepai.base.pojos.dto.SystemConfigOneDTO;
import com.kepai.base.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/base/system")
public class BaseSystemConfigController {

    @Autowired
    SystemConfigService systemConfigService;


    /**
     * 获取配置列表
     *
     * @param dto
     * @return
     */
    @RequestMapping("/get_config_list")
    public ApiResp getConfigList(@Valid @RequestBody PageDTO dto) {
        Page<BaseSystemConfig> page = systemConfigService.getListByPage(dto);
        return ApiResp.respOK(page);
    }


    /**
     * 添加、更新配置
     *
     * @param dto
     * @return
     */
    @RequestMapping("/update_config")
    public ApiResp updateConfig(@Valid @RequestBody SystemConfigDTO dto) {
        BaseSystemConfig config = BeanUtil.copyProperties(dto, BaseSystemConfig.class);

        if (config.getId() == null) {
            config.setCreateTime(DateUtil.date());
        }
        config.setUpdateTime(DateUtil.date());
        systemConfigService.saveOrUpdate(config);
        return ApiResp.respOK("");
    }


    /**
     * 删除配置
     *
     * @param dto
     * @return
     */
    @RequestMapping("/del_config")
    public ApiResp configClose(@Valid @RequestBody IdDTO dto) {
        UpdateWrapper<BaseSystemConfig> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_deleted", 1);
        updateWrapper.eq("id", dto.getId());
        systemConfigService.update(updateWrapper);
        return ApiResp.respOK("");
    }


    /**
     * 获取单个配置
     *
     * @return
     */
    @RequestMapping("/get_config_one")
    public ApiResp configOne(@Valid @RequestBody SystemConfigOneDTO dto) {
        BaseSystemConfig config = systemConfigService.getConfigOne(dto.getKey(), "");
        return ApiResp.respOK(config);
    }


}
