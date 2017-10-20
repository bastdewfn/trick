package com.pfq.trick;

import com.pfq.trick.config.TrickProperties;

import java.lang.reflect.Proxy;

/**
 * Created by huwei on 2017/8/4.
 */
public class TrickFactoryProxy {

    public TrickFactoryProxy(){}

    public static  <T>  T CreateInstance(Class<T> clazz, TrickProperties trickProperties,ITrickInterceptor trickInterceptor) throws Exception {
        Object result = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new TrickProxyInstance<T>(clazz,trickProperties,trickInterceptor));
        return clazz.isInstance(result)?clazz.cast(result):null;
    }
}
