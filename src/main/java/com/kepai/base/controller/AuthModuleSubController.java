package com.kepai.base.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kepai.base.pojos.ApiResp;
import com.kepai.base.pojos.dao.AuthModulesub;
import com.kepai.base.pojos.dto.IdDTO;
import com.kepai.base.pojos.dto.IdStateDTO;
import com.kepai.base.pojos.dto.ModuleSubDTO;
import com.kepai.base.pojos.dto.SortDTO;
import com.kepai.base.service.AuthModuleSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author hao
 */
@RestController
@RequestMapping("/auth/modulesub")
public class AuthModuleSubController {

    @Autowired
    AuthModuleSubService authModuleSubService;


    /**
     * 模块列表
     *
     * @return
     */
    @RequestMapping("/list")
    public ApiResp moduleList(@Valid @RequestBody IdStateDTO dto) {

        QueryWrapper<AuthModulesub> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("seq");
        if (!StrUtil.isEmpty(dto.getId())) {
            queryWrapper.eq("module_id", dto.getId());
        }
        if (!StrUtil.isEmpty(dto.getState())) {
            queryWrapper.eq("is_deleted", dto.getState());
        }
        Page<AuthModulesub> page = dto.pageObj();
        authModuleSubService.page(page, queryWrapper);

        return ApiResp.respOK(page);
    }


    /**
     * 增加、修改模块
     *
     * @return
     */
    @RequestMapping(value = {"/add", "/update"})
    public ApiResp moduleAdd(@Valid @RequestBody ModuleSubDTO dto) {
        authModuleSubService.saveOrUpdate(dto);
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
        authModuleSubService.changeState(dto.getId());
        return ApiResp.respOK("");
    }


    /**
     * 模块排序
     *
     * @return
     */
    @RequestMapping("/sort")
    public ApiResp moduleSort(@Valid @RequestBody SortDTO dto) {
        authModuleSubService.getBaseMapper().changeSeqById(dto.getId(), dto.getSeq());
        return ApiResp.respOK("");
    }


}
