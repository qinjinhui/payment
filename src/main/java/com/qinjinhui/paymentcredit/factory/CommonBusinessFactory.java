package com.qinjinhui.paymentcredit.factory;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @Author qinjinhui
 * @Date 2023/12/27
 * @Describe
 **/
public class CommonBusinessFactory implements BusinessFactory<BusinessType, CommonBusinessHandler> {

    private final Map<String, BusinessFactory> factoryMap = Maps.newHashMap();

    private final static CommonBusinessFactory FACTORY = new CommonBusinessFactory();

    public CommonBusinessFactory () {

    }

    @Override
    public void doRegister(BusinessType type, CommonBusinessHandler handler) {
        BusinessFactory factory =factoryMap.getOrDefault(type.getFactoryType(),new DefaultBusinessFactory());
        factory.doRegister(type, handler);
        factoryMap.put(type.getFactoryType(), factory);
    }

    @Override
    public <R, S> List<S> doExecute(BusinessType type, R request) {
        BusinessFactory factory = factoryMap.get(type.getFactoryType());
        if (null == factory) {
            //异常
        }
        return factory.doExecute(type, request);
    }

    public static <R, S> S executeSign(BusinessType type, R request) {
        int len = FACTORY.size(type);
        if (len != 1) {
            //异常
        }
        List<S> executeHandleList = FACTORY.doExecute(type, request);
        return executeHandleList.get(0);
    }

    @Override
    public int size(BusinessType type) {
        BusinessFactory factory = factoryMap.get(type.getFactoryType());
        return null == factory ? 0 : factory.size(type);
    }

    public static void register(BusinessType type, CommonBusinessHandler handler) {
        FACTORY.doRegister(type, handler);
    }
}

