package com.qinjinhui.paymentcredit.core.aop;

import com.qinjinhui.paymentcredit.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author qinjinhui
 * @Date 2022/11/16
 * @Describe  控制器AOP切面
 **/
@Aspect
@Component
@Order(-1)
@Slf4j
public class ApiControllerAspect {

    @Autowired
    ValidationUtils validationUtils;

    @Pointcut("@annotation(com.qinjinhui.paymentcredit.annotations.ApiController)")
    public void pointcut(){

    }

    /**
     * 切入点
     * @param joinPoint
     * @throws Throwable
     */
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) throws  Throwable{
        log.info("来到AOP切面");
        Object[] args = joinPoint.getArgs();
        validationUtils.validation(args[0]);
    }
}
