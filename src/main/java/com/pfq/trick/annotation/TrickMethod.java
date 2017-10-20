package com.pfq.trick.annotation;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Created by huwei on 2017/8/7.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TrickMethod {
    @AliasFor("value")
    String remotePath() default "";
    @AliasFor("remoteUrl")
    String value() default "";
    String httpType() default "";
}
