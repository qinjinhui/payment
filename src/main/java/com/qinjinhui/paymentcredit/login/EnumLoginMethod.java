package com.qinjinhui.paymentcredit.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author qinjinhui
 * @Date 2024/1/8
 * @Describe  登录方式
 **/
@Getter
@AllArgsConstructor
public enum EnumLoginMethod {

    PASSWORD("PASSWORD","验证码登录"),
    VERIFICATION_CODE("VERIFICATION_CODE","验证码登录");
    private String code;
    private String name;
}
