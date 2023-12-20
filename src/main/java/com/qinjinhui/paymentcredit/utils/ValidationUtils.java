package com.qinjinhui.paymentcredit.utils;

import cn.hutool.core.util.ReflectUtil;
import com.qinjinhui.paymentcredit.validation.ValidationFactory;
import org.apache.el.ExpressionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.el.ExpressionFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @Author qinjinhui
 * @Date 2022/11/17
 * @Describe
 **/
@Component
public class ValidationUtils {

    @Autowired
    ValidationFactory validationFactory;

   private final static  String   PACKAGE_VALIDATION_CLASS= "com.qinjinhui.paymentcredit.validation.annotations";


    /**
     * 执行注解验证
     * @param checkAnnotaion
     * @return
     */
    public boolean validation(Object checkAnnotaion){
        //加载指定包名下所有的注解类  TODO:项目启动获取注解类加载
        Class[] classByPackage = ClassUtils.getClassByPackage(PACKAGE_VALIDATION_CLASS);
        for (Field field : Arrays.asList(ReflectUtil.getFields(checkAnnotaion.getClass()))){
            for (Object annotation : Arrays.asList(field.getDeclaredAnnotations())) {
                try {
                   checkAnnotations(field,annotation);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 校验注解验证
     * @param field    要验证字段
     * @param checkAnnotaion   验证注解类
     * @return
     * @throws IllegalAccessException
     */
    public boolean checkAnnotations(Field field,Object checkAnnotaion) throws IllegalAccessException {
        return validationFactory
                .getValidationInteList(((Annotation) checkAnnotaion)
                .annotationType())
                .getBasic(field.get(checkAnnotaion));
    }

}
