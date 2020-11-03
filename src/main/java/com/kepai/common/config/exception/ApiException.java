package com.kepai.common.config.exception;

import com.kepai.common.base.ApiResp;

/**
 * @author huang
 * @ProjectName health
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2017/8/8
 * @note 这里写文件的详细功能和改动
 * @note
 */
public class ApiException extends RuntimeException {

    private ApiResp apiResp;

    public ApiException(String message) {
        super(message);
        this.apiResp = ApiResp.respCust(102, message);
    }

    public ApiException(int code, String message) {
        this.apiResp = ApiResp.respCust(code, message);
    }

    public ApiException(ApiResp apiResp) {
        this.apiResp = apiResp;
    }

    public ApiResp getApiResp() {
        return apiResp;
    }
}
