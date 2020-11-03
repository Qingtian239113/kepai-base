package com.kepai.base.config;


import com.kepai.base.aspect.OperationAspect;
import com.kepai.base.config.exception.SpringExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author hao
 */
@Configuration
@ComponentScan(
        basePackages = {
                "com.kepai.base.service",
                "com.kepai.base.controller"
        },

        basePackageClasses = {
                SpringExceptionHandler.class,
                OperationAspect.class
        })
@MapperScan("com.kepai.base.mapper")
public class KepaiCommonConfiguration {
}
