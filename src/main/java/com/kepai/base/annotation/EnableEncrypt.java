package com.kepai.base.annotation;

import com.kepai.base.config.encrypt.EncryptConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用加解密
 *
 * @author hao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({EncryptConfiguration.class})
public @interface EnableEncrypt {
}
