package com.kepai.common.aspect;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.kepai.common.annotation.OperationLog;
import com.kepai.common.base.TokenParse;
import com.kepai.common.service.SystemLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;


/**
 * @author hao
 * @ProjectName CmccSpring
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2019-07-15
 * @note 这里写文件的详细功能和改动
 * @note
 */
@Aspect
@Component
public class OperationAspect {

    @Autowired
    SystemLogService systemLogService;


    @Pointcut("@annotation(com.kepai.common.annotation.OperationLog)")
    public void methodArgs() {

    }


    /**
     * 获取操作日志说明
     *
     * @param joinPoint
     */
    @Before("methodArgs()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OperationLog operationLog = method.getAnnotation(OperationLog.class);
        String subject = operationLog.subject();

        TokenParse tokenParse = TokenParse.parse();
        Object arg = null;

        if (joinPoint.getArgs().length > 0) {
            arg = joinPoint.getArgs()[0];
            Map<String, Object> map = BeanUtil.beanToMap(arg);
            if (!StrUtil.isEmpty(subject)) {
                subject = StrUtil.format(subject, map);
            }
        }

        systemLogService.addOperationLog(Integer.parseInt(tokenParse.getId()),
                operationLog.title(), subject, arg);
    }


}
