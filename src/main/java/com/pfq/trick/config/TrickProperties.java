package com.pfq.trick.config;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huwei on 2017/8/4.
 */
@Configuration
@SuppressWarnings("unchecked")
public class TrickProperties {

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public Map<String, TrickMethodProperties> getMethod() {
        return method;
    }

    public void setMethod(Map<String, TrickMethodProperties> method) {
        this.method = method;
    }

    private String className;
    private String remoteUrl;
    private String httpType;


    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    private String timeout;



    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    private String bean;

    public String getHttpType() {
        return httpType;
    }

    public void setHttpType(String httpType) {
        this.httpType = httpType;
    }

    private Map<String,TrickMethodProperties> method=new HashMap();

    public  static   class TrickMethodProperties {

        private String httpType;

        public String getHttpType() {
            return httpType;
        }

        public void setHttpType(String httpType) {
            this.httpType = httpType;
        }

        private  String methodName;

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public String getRemotePath() {
            return remotePath;
        }

        public void setRemotePath(String remotePath) {
            this.remotePath = remotePath;
        }

        private String remotePath;


    }

}
