package com.qinjinhui.paymentcredit.factory;

/**
 * @Author qinjinhui
 * @Date 2024/1/8
 * @Describe  业务执行公共接口
 **/
public interface BusinessExecute<T,S> {


    String tradIngCode ();

     S  businessExecute(T request);
}
