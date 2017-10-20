package com.pfq.trick;

import java.lang.reflect.Method;

/**
 * Created by huwei on 2017/10/10.
 */
public class TrickInvokeInfo {
    private Method method;
    private String remoteUrl;
    private String path;
    private Object[] args;
    private String httpType;
    private long useTime;
    private Exception exception;
    private Object request;
    private Object response;

    /**
     * 调用时 拦截到地实体方法映射
     * @return
     */
    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * 远程地址，与getPath一起使用
     * @return
     */
    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    /**
     * 对应调用地址，与getRemoteUrl一起使用
     * @return
     */
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 方法执行的参数
     * @return
     */
    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    /**
     * HTTP请求方式
     * @return
     */
    public String getHttpType() {
        return httpType;
    }

    public void setHttpType(String httpType) {
        this.httpType = httpType;
    }

    /**
     * 远程调用时间，after方法时值为0
     * @return
     */
    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }

    /**
     * 执行有异常时，异常信息，abnormal方法时才有值
     * @return
     */
    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    /**
     * 执行的结果，请求的实体，对方法调用时args最后一个参数
     * @return
     */
    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    /**
     * 执行的结果，after方法时值为null
     * @return
     */
    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

}
