package com.kepai.base.config.encrypt;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author hao
 */
@Configuration
@ComponentScan(basePackageClasses = {
        EncryptRequestBodyAdvice.class,
        EncryptResponseBodyAdvice.class
})
public class EncryptConfiguration {
}
