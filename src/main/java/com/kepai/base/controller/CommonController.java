package com.kepai.base.controller;

import com.kepai.base.pojos.ApiResp;
import com.kepai.base.pojos.dto.UserLoginDTO;
import com.kepai.base.pojos.vo.LoginVo;
import com.kepai.base.service.AuthManagerService;
import com.kepai.base.service.TimedCacheService;
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
