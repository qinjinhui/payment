package com.qinjinhui.paymentcredit.utils;


import com.qinjinhui.paymentcredit.controller.TestController;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author qinjinhui
 * @Date 2023/9/9
 * @Describe
 **/
public class MyControllerUtils {

    public static String getRequestMappingPath(Class<?> controllerClass) {
        RequestMapping controllerRequestMapping = AnnotatedElementUtils.findMergedAnnotation(controllerClass, RequestMapping.class);
        if (controllerRequestMapping != null) {
            String[] controllerPaths = controllerRequestMapping.value();
            if (controllerPaths.length > 0) {
                return controllerPaths[0];
            }
        }

        return "";
    }

    public static String getInterfaceRequestMappingPath(Class<?> controllerClass, Class<?> interfaceClass) {
//        RequestMapping interfaceRequestMapping = interfaceClass.getDeclaredAnnotation(RequestMapping.class);
//        if (interfaceRequestMapping != null) {
//            String[] interfacePaths = interfaceRequestMapping.value();
//            if (interfacePaths.length > 0) {
//                return interfacePaths[0];
//            }
//        }
        // 获取接口中的所有方法
        Method[] methods = interfaceClass.getDeclaredMethods();
        // 遍历方法，查找 @RequestMapping 注解
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == RequestMapping.class) {
                    RequestMapping requestMapping = (RequestMapping) annotation;
                    String[] values = requestMapping.value();
                    for (String value : values) {
                        System.out.println("RequestMapping value: " + value);
                    }
                }
            }
        }

        return "";
    }



    public static void main(String[] args) {
        Class<?> controllerClass = TestController.class;
        Class<?>[] interfaceClass = controllerClass.getInterfaces();
        Class<?> interfaceClass1 = Arrays.stream(interfaceClass).findFirst().get();
        String controllerPath = getRequestMappingPath(controllerClass);
        String interfacePath = getInterfaceRequestMappingPath(controllerClass,interfaceClass1);

        System.out.println("Controller Path: " + controllerPath);
        System.out.println("Interface Path: " + interfacePath);
    }
}

