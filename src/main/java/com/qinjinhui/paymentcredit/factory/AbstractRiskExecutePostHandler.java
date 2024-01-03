package com.qinjinhui.paymentcredit.factory;

import java.util.List;

/**
 * @Author qinjinhui
 * @Date 2024/1/3
 * @Describe
 **/
public class AbstractRiskExecutePostHandler<T,S> extends AbstractBusinessHandler <T,S> {
    @Override
    protected List<BusinessType> businessTypeList() {
        return null;
    }
}
