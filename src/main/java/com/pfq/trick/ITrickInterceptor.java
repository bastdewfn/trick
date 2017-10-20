package com.pfq.trick;

import java.util.EventListener;

/**
 * 可拦截trick远程调用时信息 Created by huwei on 2017/10/9.
 */

public interface ITrickInterceptor  {
    /**
     * 远程调用前执行，可终止调用
     * @param trickInvokeInfo 获取调用信息
     * @return 如果为true 则会终止后面调用
     */
    boolean before(TrickInvokeInfo trickInvokeInfo);

    /**
     * 远程调用后执行
     * @param trickInvokeInfo 获取调用信息
     */
    void after(TrickInvokeInfo trickInvokeInfo);

    /**
     * 远程调用执行异常，错误时执行
     * @param trickInvokeInfo 获取调用信息
     */
    void abnormal(TrickInvokeInfo trickInvokeInfo);
}
