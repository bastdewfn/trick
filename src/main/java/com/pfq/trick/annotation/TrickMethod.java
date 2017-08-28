package com.pfq.trick.annotation;


/**
 * Created by huwei on 2017/8/7.
 */
public @interface TrickMethod {
    String remotePath() default "";
    String httpType() default "";
}
