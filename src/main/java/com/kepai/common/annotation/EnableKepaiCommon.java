package com.kepai.common.annotation;


import com.kepai.common.config.KepaiCommonConfiguration;
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
