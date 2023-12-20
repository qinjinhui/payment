package com.qinjinhui.paymentcredit.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import java.lang.reflect.Method;
import java.util.Map;
/**
 * @Author qinjinhui
 * @Date 2023/9/8
 * @Describe
 **/
public class ControllerURLScanner {

    @Autowired
    private ApplicationContext applicationContext;

    public void scanControllerURLs() {
        RequestMappingHandlerMapping handlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            HandlerMethod handlerMethod = entry.getValue();
            Class<?> controllerClass = handlerMethod.getBeanType();

            // 判断是否有@Controller注解
            if (AnnotatedElementUtils.hasAnnotation(controllerClass, Controller.class)) {
                // 获取@Controller注解的value，通常是Controller的URL前缀
                String baseUrl = getBaseUrl(controllerClass);

                // 获取方法上的@RequestMapping注解
                Method method = handlerMethod.getMethod();
                RequestMapping methodMapping = method.getAnnotation(RequestMapping.class);

                if (methodMapping != null) {
                    String[] methodUrls = methodMapping.value();

                    for (String methodUrl : methodUrls) {
                        // 拼接baseUrl和方法的URL
                        String fullUrl = baseUrl + methodUrl;
                        System.out.println("URL: " + fullUrl);
                    }
                }
            }
        }
    }

    private String getBaseUrl(Class<?> controllerClass) {
        RequestMapping classMapping = AnnotatedElementUtils.findMergedAnnotation(controllerClass, RequestMapping.class);
        if (classMapping != null) {
            return String.join("", classMapping.value());
        }
        return "";
    }


}

