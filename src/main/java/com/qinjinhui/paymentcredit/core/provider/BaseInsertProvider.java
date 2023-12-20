package com.qinjinhui.paymentcredit.core.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author qinjinhui
 * @Date 2022/9/23
 * @Describe       * 常见问题   ：  	           //providerMethod在赋值时的条件是 ：
 *      * 	           //1、当前方法与类中所有方法的hash值进行对比
 *      * 	           //2、方法的参数必须少于2个
 *      * 	           //3、方法的返回值必须为字符串
 **/
@Slf4j
public class BaseInsertProvider<T> {
    /**
     * @param record
     * @return
     */



    public String baseInsert(Object record){
        return null;
    }
}
