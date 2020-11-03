package com.kepai.common.config.encrypt.annotation;

import java.lang.annotation.*;

/**
 * @author hao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Decrypt
@Encrypt
public @interface EnAndDecrypt {
}
