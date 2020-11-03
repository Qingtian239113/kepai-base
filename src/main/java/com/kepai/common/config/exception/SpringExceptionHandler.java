package com.kepai.common.config.exception;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.log.LogFactory;
import com.kepai.common.base.ApiResp;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;

/**
 * @author huang
 */
@RestControllerAdvice
public class SpringExceptionHandler {

    /**
     * 自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ApiException.class)
    public ApiResp handleApiException(ApiException e) {
        return e.getApiResp();
    }


    /**
     * 参数校验
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ValidateException.class)
    public ApiResp validateException(ValidateException e) {
        return ApiResp.respNeedParam(e.getMessage());
    }

    /**
     * IO异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IOException.class)
    public ApiResp handleIoException(IOException e) {
        return ApiResp.respCust(90, "IO异常");
    }


    /**
     * 请求参数不合法
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ApiResp handleBindException(BindException e) {
        FieldError error = e.getFieldError();
        String msg = "请求参数不合法，请检查参数";
        if (error != null) {
            msg = error.getDefaultMessage();
        }
        return ApiResp.respCust(91, msg);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResp handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ApiResp.respCust(91, "请求参数不合法，请检查参数");
    }

    /**
     * 不支持的请求方式
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResp handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ApiResp.respCust(92, "不支持的请求方式，请检查接口文档");
    }


    /**
     * 参数格式异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResp argumentValidException(MethodArgumentNotValidException e) {
        String res = "请求参数不合法，请检查参数";
        if (e.getBindingResult().getFieldError() != null) {
            res = e.getBindingResult().getFieldError().getDefaultMessage();
        }
        return ApiResp.respCust(91, res);

    }


    /**
     * sql语句异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    public ApiResp handleBadSqlGrammarException(BadSqlGrammarException e) {
        return ApiResp.respCust(93, "sql语句异常：" + e.getLocalizedMessage());
    }


    /**
     * 请求路径不存在
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResp handlerNoFoundException(NoHandlerFoundException e) {
        return ApiResp.respCust(94, "请求路径不存在，请检查接口文档");
    }


    /**
     * 数据已存在
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ApiResp handlerDuplicateKeyException(DuplicateKeyException e) {
        return ApiResp.respCust(95, "数据已存在，请检查");
    }


    /**
     * 请求频繁
     *
     * @param e
     * @return
     */
    @ExceptionHandler(QueryTimeoutException.class)
    public ApiResp handlerQueryTimeoutException(DuplicateKeyException e) {
        return ApiResp.respCust(97, "操作频繁，请稍后重试!");
    }


    /**
     * 其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ApiResp handle(Exception e) {
        ApiResp apiResp = ApiResp.respCust(96, e.getMessage());
        LogFactory.get().info(e);
        return apiResp;
    }


}
