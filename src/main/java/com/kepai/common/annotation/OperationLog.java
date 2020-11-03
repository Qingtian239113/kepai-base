package com.kepai.common.annotation;

import java.lang.annotation.*;

/**
 * @author hao
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {

    String title() default "";

    String subject() default "";


}
