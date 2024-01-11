package com.qinjinhui.paymentcredit.factory;

import java.util.List;

/**
 * @Author qinjinhui
 * @Date 2024/1/3
 * @Describe   登录抽象
 **/
public abstract class AbstractLoginFactorBuildHandler<T,S> extends AbstractBusinessHandler<T,S> implements BusinessExecute<T,S>  {


    @Override
    protected List<BusinessType> businessTypeList() {
        return BusinessType.build(EnumFactoryType.STANDARD_LOGIN.getCode(), new Object[]{this.tradIngCode()});
    }

    @Override
    public S execute(T request) {
        return businessExecute(request);
    }
}
