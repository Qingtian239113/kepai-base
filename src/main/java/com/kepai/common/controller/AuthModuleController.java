package com.kepai.common.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kepai.common.base.ApiResp;
import com.kepai.common.pojos.dao.AuthModule;
import com.kepai.common.pojos.dto.IdDTO;
import com.kepai.common.pojos.dto.IdStateDTO;
import com.kepai.common.pojos.dto.ModuleDTO;
import com.kepai.common.pojos.dto.SortDTO;
import com.kepai.common.service.AuthModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author hao
 */
@RestController
@RequestMapping("/auth/module")
public class AuthModuleController {

    @Autowired
    AuthModuleService authModuleService;


    /**
     * 模块列表
     *
     * @return
     */
    @RequestMapping("/list")
    public ApiResp moduleList(@Valid @RequestBody IdStateDTO dto) {

        QueryWrapper<AuthModule> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("seq");
        if (!StrUtil.isEmpty(dto.getState())) {
            queryWrapper.eq("is_deleted", dto.getState());
        }
        Page<AuthModule> page = dto.pageObj();
        authModuleService.page(page, queryWrapper);

        return ApiResp.respOK(page);
    }


    /**
     * 增加、修改模块
     *
     * @return
     */
    @RequestMapping(value = {"/add", "/update"})
    public ApiResp moduleAdd(@Valid @RequestBody ModuleDTO dto) {
        authModuleService.saveOrUpdate(dto);
        return ApiResp.respOK("");
    }


    /**
     * 改变模块状态
     *
     * @param dto
     * @return
     */
    @RequestMapping("/state")
    public ApiResp moduleState(@Valid @RequestBody IdDTO dto) {
        authModuleService.changeState(dto.getId());
        return ApiResp.respOK("");
    }


    /**
     * 模块排序
     *
     * @return
     */
    @RequestMapping("/sort")
    public ApiResp moduleSort(@Valid @RequestBody SortDTO dto) {
        authModuleService.getBaseMapper().changeSeqById(dto.getId(), dto.getSeq());
        return ApiResp.respOK("");
    }


}
