package com.pfq.trick;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.pfq.trick.annotation.TrickMethod;
import com.pfq.trick.annotation.TrickServer;
import com.pfq.trick.config.TrickProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by huwei on 2017/8/4.
 */
public class TrickProxyInstance<T> implements InvocationHandler {

    public   static  FastJsonHttpMessageConverter fastJsonHttpMessageConverter=new FastJsonHttpMessageConverter();

    static {
        MediaType mediaType= new MediaType("application","json");
        fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(mediaType));

    }

    private  RestTemplate restTemplate=new RestTemplate();


    private static final Logger LOG = LoggerFactory.getLogger(TrickProxyInstance.class);

    private Class<T> clazz;

    public TrickProxyInstance(Class<T> clazz, TrickProperties trickProperties) throws Exception {
        this.clazz = clazz;
        this.trickProperties=trickProperties;
        initPropertie();


        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        if( StringUtils.isEmpty(this.trickProperties.getTimeout())){
        requestFactory.setConnectTimeout(5000);
        }
        else{
            requestFactory.setConnectTimeout(Integer.parseInt(this.trickProperties.getTimeout()));
        }
        // 数据读取超时时间，即SocketTimeout
        //requestFactory.setReadTimeout(5000);
        // 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的

        restTemplate.setRequestFactory(requestFactory);
        restTemplate.getMessageConverters().add(0,fastJsonHttpMessageConverter);
    }

    private  TrickProperties trickProperties;


    private  void initPropertie() throws Exception {

        TrickServer trickServer= clazz.getAnnotation(TrickServer.class);
        if(this.trickProperties==null) {
            trickProperties = new TrickProperties();
            if(trickServer==null)
            {
                throw new Exception("未添加远程映射");
            }

       }
        if(StringUtils.isEmpty(trickProperties.getHttpType())&&trickServer!=null) {
            trickProperties.setHttpType(trickServer.httpType());
        }
        if(StringUtils.isEmpty(trickProperties.getRemoteUrl())&&trickServer!=null) {
            trickProperties.setRemoteUrl(trickServer.remoteUrl());
        }
        if(this.trickProperties.getTimeout()==null&&trickServer!=null)
            this.trickProperties.setTimeout(String.valueOf(trickServer.timeOut()));


        for (Method method:clazz.getMethods()) {
            TrickProperties.TrickMethodProperties trickMethodProperties = trickProperties.getMethod().get(method.getName());
            if (trickMethodProperties == null) {
                trickMethodProperties = new TrickProperties.TrickMethodProperties();
                trickProperties.getMethod().put(method.getName(), trickMethodProperties);

                TrickMethod  trickMethod= method.getDeclaringClass().getAnnotation(TrickMethod.class);
                if(trickMethod!=null){
                    trickMethodProperties.setHttpType(trickMethod.httpType());
                    trickMethodProperties.setRemotePath(trickMethod.remotePath());
                }
            }
            if(StringUtils.isEmpty(trickMethodProperties.getHttpType()))
                trickMethodProperties.setHttpType(trickProperties.getHttpType());
        }


    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String methodName=method.getName();
        if ("toString".equals(methodName))
            return trickProperties.getClassName();

        StopWatch sw = new StopWatch();

        String url=trickProperties.getRemoteUrl();
        String path="";
        String httpType="";
        try {

            TrickProperties.TrickMethodProperties trickMethodProperties=trickProperties.getMethod().get(methodName);
            path=trickMethodProperties.getRemotePath();
            httpType=trickMethodProperties.getHttpType();
            if(StringUtils.isEmpty(httpType))
                httpType="POST";
            if(StringUtils.isEmpty(path))
                path=methodName;

            Object param=null;
            if(args!=null&&args.length>0)
                param=args[args.length-1];

            Object rsp=null;

            if(args==null)
                args=new Object[]{};


            sw.start();
            if("POST".equals(httpType)){
                rsp= restTemplate.postForObject(url+path,param,method.getReturnType(),args);
            }

            else if("GET".equals(httpType)){
                rsp= restTemplate.getForObject(url+path,method.getReturnType(),args);
            }
            sw.stop();

            LOG.info(String.format("\r\nTrick远程访问域名:%s, 地址:%s, 耗时:%d ms, 请求类型:%s\r\n请求参数:%s\r\n返回结果:%s",url,path,sw.getTotalTimeMillis(),httpType, JSON.toJSON(args),JSON.toJSON(rsp)));
            return  rsp;

        } catch (Exception ex) {
            sw.stop();
            LOG.error(String.format("\r\nTrick远程访问域名:%s, 地址:%s, 耗时:%d ms, 请求类型:%s\r\n请求参数:%s",url,path,sw.getTotalTimeMillis(),httpType, JSON.toJSON(args)),ex);
        }
        return null;
    }
}
