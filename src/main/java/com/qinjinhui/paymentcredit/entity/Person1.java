package com.qinjinhui.paymentcredit.entity;

import lombok.Data;

/**
 * @Author qinjinhui
 * @Date 2023/5/4
 * @Describe
 **/
// 创建Person类
    @Data
public class Person1 {
    private String name;
    private int age=name.length();

    public Person1(String name){
        this.name=name;
    }





    // setter和getter方法省略
}

