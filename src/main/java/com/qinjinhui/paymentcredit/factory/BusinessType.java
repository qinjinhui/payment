package com.qinjinhui.paymentcredit.factory;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author qinjinhui
 * @Date 2023/12/27
 * @Describe  公共抽象类型
 **/
@Data
public class BusinessType {

    Object handlerType;

    String  factoryType;

    public BusinessType(final String factoryType, final String handlerType) {
        this.factoryType=factoryType;
        this.handlerType=handlerType;
    }

    public static List<BusinessType> build(String factoryType, Object... handlerType) {
        return Lists.newLinkedList();
    }
}
