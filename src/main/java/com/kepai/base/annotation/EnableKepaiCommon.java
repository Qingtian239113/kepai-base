package com.kepai.base.annotation;


import com.kepai.base.config.KepaiCommonConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author hao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({KepaiCommonConfiguration.class})
public @interface EnableKepaiCommon {
}
