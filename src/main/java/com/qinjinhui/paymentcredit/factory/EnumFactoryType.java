package com.qinjinhui.paymentcredit.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author qinjinhui
 * @Date 2024/1/3
 * @Describe
 **/
@Getter
@AllArgsConstructor
public enum EnumFactoryType {

    STANDARD_LOGIN("standard$login","登录执行器");
    private String code;
    private String name;
}
