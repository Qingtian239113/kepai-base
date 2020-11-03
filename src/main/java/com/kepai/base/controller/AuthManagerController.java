package com.kepai.base.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kepai.base.pojos.ApiResp;
import com.kepai.base.pojos.TokenParse;
import com.kepai.base.pojos.dao.AuthManager;
import com.kepai.base.pojos.dto.AuthUserDTO;
import com.kepai.base.pojos.dto.IdDTO;
import com.kepai.base.pojos.dto.PageDTO;
import com.kepai.base.pojos.dto.UpdateManagerDTO;
import com.kepai.base.service.AuthManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author hao
 */
@RestController
@RequestMapping("/auth/manager")
public class AuthManagerController {

    @Autowired
    AuthManagerService authManagerService;


    /**
     * 人员列表
     *
     * @return
     */
    @RequestMapping("/list")
    public ApiResp userList(@Valid @RequestBody PageDTO dto) {

        Page<Map<String, Object>> page = dto.pageObj();
        List<Map<String, Object>> mapList = authManagerService.getBaseMapper().getUserList(page);
        page.setRecords(mapList);

        return ApiResp.respOK(page);
    }


    /**
     * 增加、修改人员
     *
     * @return
     */
    @RequestMapping(value = {"/add", "/update"})
    public ApiResp moduleAdd(@Valid @RequestBody AuthUserDTO dto) {
        authManagerService.saveOrUpdate(dto);
        return ApiResp.respOK("");
    }


    /**
     * 改变人员状态
     *
     * @param dto
     * @return
     */
    @RequestMapping("/state")
    public ApiResp moduleState(@Valid @RequestBody IdDTO dto) {
        authManagerService.changeState(dto.getId());
        return ApiResp.respOK("");
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping("/get_info")
    public ApiResp getUserInfo() {
        TokenParse tokenParse = TokenParse.parse();
        AuthManager authManager = authManagerService.getByUserId(tokenParse.getId());
        authManager.setPassword(null);
        return ApiResp.respOK(authManager);
    }


    /**
     * 更新用户信息
     *
     * @return
     */
    @RequestMapping("/update_info")
    public ApiResp updateUserInfo(@Valid @RequestBody UpdateManagerDTO dto) {
        TokenParse tokenParse = TokenParse.parse();
        AuthUserDTO authUserDTO = BeanUtil.copyProperties(dto, AuthUserDTO.class);
        authUserDTO.setId(Integer.parseInt(tokenParse.getId()));
        authManagerService.saveOrUpdate(authUserDTO);
        return ApiResp.respOK("");
    }


    /**
     * 获取1级模块
     *
     * @return
     */
    @RequestMapping("/moduleOne")
    public ApiResp moduleOne() {
        TokenParse tokenParse = TokenParse.parse();
        List<Map<String, Object>> moduleList = authManagerService.getModuleOneByUserId(tokenParse.getId());
        return ApiResp.respOK(moduleList);
    }


    /**
     * 获取二级模块
     *
     * @return
     */
    @RequestMapping("/moduleTwo")
    public ApiResp moduleTwo(@Valid @RequestBody IdDTO dto) {
        TokenParse tokenParse = TokenParse.parse();
        List<Map<String, Object>> moduleList = authManagerService.getModuleTwoByUserId(tokenParse.getId(), dto.getId());
        return ApiResp.respOK(moduleList);
    }


}
