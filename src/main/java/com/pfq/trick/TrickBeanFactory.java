package com.pfq.trick;

import com.pfq.trick.config.TrickConfig;
import com.pfq.trick.config.TrickProperties;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by huwei on 2017/8/8.
 */
public class TrickBeanFactory implements FactoryBean<Object>, InitializingBean, DisposableBean {

    public TrickBeanFactory(){};

    @Override
    public void destroy() throws Exception {
    }

    @Autowired
    private TrickConfig trickConfig;
    @Autowired(required = false)
    private ITrickInterceptor trickInterceptor;

    @Override
    public Object getObject() throws Exception {
        return proxyObj;
    }

    @Override
    public Class<?> getObjectType() {
        if(trickProperties==null)
            return  null;
        try {
            return  Class.forName(trickProperties.getClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public boolean isSingleton() {        return false;
    }

    private TrickProperties trickProperties;

    private  Object   proxyObj;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            trickProperties=   trickConfig.getTrick().get(beanName);
            proxyObj=  TrickFactoryProxy.CreateInstance(Class.forName(trickProperties.getClassName()),trickProperties,trickInterceptor);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    private String beanName;
}
