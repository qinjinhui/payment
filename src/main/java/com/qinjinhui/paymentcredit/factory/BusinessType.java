package com.qinjinhui.paymentcredit.factory;

import lombok.Builder;
import lombok.Data;

/**
 * @Author qinjinhui
 * @Date 2023/12/27
 * @Describe  公共抽象类型
 **/
@Data
@Builder
public class BusinessType {

    Object handlerType;

    String  factoryType;
}
