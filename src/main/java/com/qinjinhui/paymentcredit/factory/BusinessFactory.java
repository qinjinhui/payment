package com.qinjinhui.paymentcredit.factory;

import java.util.List;

/**
 * @Author qinjinhui
 * @Date 2023/12/27
 * @Describe  工厂公共接口
 **/
public  interface BusinessFactory<T,H> {

    /**
     * 注册器
     * @param type
     * @param handler
     */
     void  doRegister(T type, H handler);

    /**
     * 业务执行
     * @param type
     * @param request
     * @param <R>
     * @param <S>
     * @return
     */
     <R,S> List<S> doExecute(T type, R request);

    /**
     * 处理器数量
     * @param type
     * @return
     */
     int size(T type);
}
