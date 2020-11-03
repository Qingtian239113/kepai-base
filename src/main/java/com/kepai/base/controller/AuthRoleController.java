package com.kepai.base.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kepai.base.pojos.ApiResp;
import com.kepai.base.pojos.dao.AuthRole;
import com.kepai.base.pojos.dto.IdDTO;
import com.kepai.base.pojos.dto.IdStateDTO;
import com.kepai.base.pojos.dto.RoleDTO;
import com.kepai.base.pojos.dto.RoleModulesubDTO;
import com.kepai.base.pojos.vo.ModulesubVo;
import com.kepai.base.service.AuthModuleSubService;
import com.kepai.base.service.AuthRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hao
 */
@RestController
@RequestMapping("/auth/role")
public class AuthRoleController {

    @Autowired
    AuthRoleService authRoleService;
    @Autowired
    AuthModuleSubService authModuleSubService;


    /**
     * 角色列表
     *
     * @return
     */
    @RequestMapping("/list")
    public ApiResp moduleList(@Valid @RequestBody IdStateDTO dto) {

        QueryWrapper<AuthRole> queryWrapper = new QueryWrapper<>();
        if (!StrUtil.isEmpty(dto.getState())) {
            queryWrapper.eq("is_deleted", dto.getState());
        }

        Page<AuthRole> page = dto.pageObj();
        authRoleService.page(page, queryWrapper);

        return ApiResp.respOK(page);
    }


    /**
     * 增加、修改角色
     *
     * @return
     */
    @RequestMapping(value = {"/add", "/update"})
    public ApiResp moduleAdd(@Valid @RequestBody RoleDTO dto) {
        authRoleService.saveOrUpdate(dto);
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
        authRoleService.changeState(dto.getId());
        return ApiResp.respOK("");
    }


    /**
     * 角色绑定的二级模块
     *
     * @return
     */
    @RequestMapping("/modulesubList")
    public ApiResp modulesubList(@Valid @RequestBody IdDTO dto) {
        Map<String, Object> body = new HashMap<>(10);
        List<Map<String, Object>> authRoleList = authRoleService.getBaseMapper().getRoleModulesubList(dto.getId());

        List<ModulesubVo> modulesubList = authModuleSubService.getBaseMapper().getModulesubList();

        body.put("selectedKeys", authRoleList);
        body.put("modulesubList", modulesubList);

        return ApiResp.respOK(body);
    }


    /**
     * 绑定二级模块
     *
     * @return
     */
    @RequestMapping("/modulesubAdd")
    public ApiResp modulesubAdd(@Valid @RequestBody RoleModulesubDTO dto) {
        authRoleService.getBaseMapper()
                .modulesubAdd(dto.getRoleId(), dto.getModulesubIds());
        return ApiResp.respOK("");
    }


}
