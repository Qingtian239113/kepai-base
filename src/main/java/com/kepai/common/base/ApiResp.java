package com.kepai.common.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author huang
 * @ProjectName health
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2017/9/4
 * @note 这里写文件的详细功能和改动
 * @note
 */
public class ApiResp<T> {

    public static int CODE_OK = 200;

    private int code;
    private String message;
    private T data;
    private long recordsTotal = 0;

    /**
     * 请求成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ApiResp<T> respOK(T data) {
        ApiResp<T> result = new ApiResp<>();
        result.code = CODE_OK;
        result.message = "请求成功";
        result.data = data;
        return result;
    }

    public static <T> ApiResp<List<T>> respOK(Page<T> page) {
        ApiResp<List<T>> result = new ApiResp<>();
        result.code = CODE_OK;
        result.message = "请求成功";
        result.data = page.getRecords();
        result.recordsTotal = page.getTotal();
        return result;
    }

    /**
     * 自定义的错误
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ApiResp<T> respCust(int code, String msg) {
        ApiResp<T> result = new ApiResp<>();
        result.code = code;
        result.message = msg;
        return result;
    }

    /**
     * 用户未登录
     *
     * @param <T>
     * @return
     */
    public static <T> ApiResp<T> respNeedLogin(String msg) {
        ApiResp<T> result = new ApiResp<>();
        result.code = 110;
        result.message = msg;
        return result;
    }

    /**
     * 请求未找到
     *
     * @param <T>
     * @return
     */
    public static <T> ApiResp<T> respNotFound() {
        ApiResp<T> result = new ApiResp<>();
        result.code = 123;
        result.message = "请求未找到";
        return result;
    }

    /**
     * 请求参数缺失
     *
     * @param <T>
     * @return
     */
    public static <T> ApiResp<T> respNeedParam(String msg) {
        ApiResp<T> result = new ApiResp<>();
        result.code = 122;
        result.message = msg;
        return result;
    }


    /**
     * 操作失败
     *
     * @param <T>
     * @return
     */
    public static <T> ApiResp<T> respDoFail() {
        ApiResp<T> result = new ApiResp<>();
        result.code = 121;
        result.message = "操作失败";
        return result;
    }


    /**
     * 根据bool返回数据
     *
     * @param <T>
     * @return
     */
    public static <T> ApiResp<T> respBool(T data, boolean isOk) {
        ApiResp<T> result = respOK(data);
        if (!isOk) {
            result = respDoFail();
        }
        return result;
    }


    /**
     * 返回数据
     *
     * @param response
     * @param bytes
     */
    public static void respBytes(HttpServletResponse response, byte[] bytes) {
        try {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 服务器异常
     *
     * @param <T>
     * @return
     */
    public static <T> ApiResp<T> respError() {
        ApiResp<T> result = new ApiResp<T>();
        result.code = 503;
        result.message = "服务器异常,请稍后再试";
        return result;
    }


    ///////////////////////////////////////////////////////////////////////////
    // setter\getter
    ///////////////////////////////////////////////////////////////////////////

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }
}
