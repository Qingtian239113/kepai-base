package com.kepai.common.controller;

import com.kepai.common.base.ApiResp;
import com.kepai.common.pojos.dto.UserLoginDTO;
import com.kepai.common.pojos.vo.LoginVo;
import com.kepai.common.service.AuthManagerService;
import com.kepai.common.service.TimedCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author hao
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    AuthManagerService authManagerService;
    @Autowired
    TimedCacheService timedCacheService;


    /**
     * 用户登录
     *
     * @param dto
     * @return
     */
    @RequestMapping("/user/login")
    public ApiResp login(@Valid @RequestBody UserLoginDTO dto, HttpSession httpSession, HttpServletRequest request) {

        timedCacheService.verifyImage(httpSession.getId(), dto.getCode());
        LoginVo loginVo = authManagerService.userLogin(dto, request);

        return ApiResp.respOK(loginVo);
    }


}
