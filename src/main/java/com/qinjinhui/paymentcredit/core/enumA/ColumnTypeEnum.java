package com.qinjinhui.paymentcredit.core.enumA;

import lombok.Data;
import lombok.Getter;

/**
 * @Author qinjinhui
 * @Date 2022/9/29
 * @Describe
 **/
public enum ColumnTypeEnum {

    STRING("varchar","字符串"),
    INT("int","数字"),
    DATE("date","时间");
    @Getter
     String code;
    @Getter
     String describe;

    ColumnTypeEnum (String code,String describe){
        this.code=code;
        this.describe=describe;
    }
}
