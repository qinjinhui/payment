package com.qinjinhui.paymentcredit.utils;

import cn.hutool.core.util.ReflectUtil;
import com.qinjinhui.paymentcredit.dao.PayAccount;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Method;

/**
 * @Author qinjinhui
 * @Date 2022/12/28
 * @Describe
 **/
@Slf4j
public class ReflectUtils<T> {


    /**
     * 反射执行类
     * @param tClass
     * @param methodByName
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     */
    public  void reflectUtils(Class<T> tClass,String methodByName) throws NoSuchMethodException, ClassNotFoundException {
        //2.通过两个参数，1 类型的class， 2.方法名称
        //返回具体方法
        try {
            methodByName=methodByName == null ? "execute" : methodByName;
            Method say = ReflectUtil.getMethodByName(tClass, methodByName);
            Object value = ReflectUtil.invoke(ApplicationContextUtil.getBean(PayAccount.class), methodByName);
        }catch (Exception e)
        {
            e.printStackTrace();
            log.error("");
        }

    }
}
