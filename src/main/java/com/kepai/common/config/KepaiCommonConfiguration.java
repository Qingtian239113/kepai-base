package com.kepai.common.config;


import com.kepai.common.aspect.OperationAspect;
import com.kepai.common.config.exception.SpringExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author hao
 */
@Configuration
@ComponentScan(
        basePackages = {
                "com.kepai.common.service",
                "com.kepai.common.controller"
        },

        basePackageClasses = {
                SpringExceptionHandler.class,
                OperationAspect.class
        })
@MapperScan("com.kepai.common.mapper")
public class KepaiCommonConfiguration {
}
