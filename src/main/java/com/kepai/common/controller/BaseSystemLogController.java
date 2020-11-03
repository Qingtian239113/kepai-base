package com.kepai.common.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kepai.common.base.ApiResp;
import com.kepai.common.pojos.dto.KeywordListDTO;
import com.kepai.common.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/base/systemlog")
public class BaseSystemLogController {

    @Autowired
    SystemLogService systemLogService;


    /**
     * 登录日志列表
     *
     * @return
     */
    @RequestMapping("/login_list")
    public ApiResp loginList(@Valid @RequestBody KeywordListDTO dto) {

        Page<Map<String, Object>> page = dto.pageMap();
        List<Map<String, Object>> list = systemLogService.listLoginLog(page, dto.getKeyword());
        page.setRecords(list);

        return ApiResp.respOK(page);
    }


    /**
     * 操作日志列表
     *
     * @return
     */
    @RequestMapping("/operation_list")
    public ApiResp operationList(@Valid @RequestBody KeywordListDTO dto) {

        Page<Map<String, Object>> page = dto.pageMap();
        List<Map<String, Object>> list = systemLogService.listOperationLog(page, dto.getKeyword());
        page.setRecords(list);

        return ApiResp.respOK(page);
    }


}
