package com.qinjinhui.paymentcredit.factory;

/**
 * @Author qinjinhui
 * @Date 2023/12/27
 * @Describe
 **/
public interface CommonBusinessHandler<T,S> extends Comparable<CommonBusinessHandler> {

    /**
     * 优先级
     * @return
     */
    Integer order();

    /**
     * 执行方法
     * @param request
     * @return
     */
    S execute(T request);
}
