package com.pfq.trick.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Created by huwei on 2017/8/7.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TrickServer {
    @AliasFor("value")
    String remoteUrl() default "";
    @AliasFor("remoteUrl")
    String value() default "";

    String httpType() default "";

    int timeOut() default 5000;
}
