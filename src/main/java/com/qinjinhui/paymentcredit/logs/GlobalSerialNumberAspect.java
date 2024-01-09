package com.qinjinhui.paymentcredit.logs;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

/**
 * @Author qinjinhui
 * @Date 2024/1/5
 * @Describe 日志切面
 **/
@Aspect
@Component
public class GlobalSerialNumberAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalSerialNumberAspect.class);

    @Before("execution(* com.qinjinhui..*.*(..))") // 指定要拦截的包路径
    public void beforeMethodExecution(JoinPoint joinPoint) {
        // 生成并设置全局流水号
        String globalSerialNumber = generateGlobalSerialNumber();
        MDC.put("全局流水号", "\u5168\u5C40\u6D41\u6C34\u53F7: " + globalSerialNumber);
        // 将其他字段添加到MDC
        // 日志记录，可选
        LOGGER.info("Setting global serial number: {}", globalSerialNumber);
    }

    private String generateGlobalSerialNumber() {
        // 返回生成的全局流水号字符串
        long milli = Instant.now().toEpochMilli();
        int nextInt = new Random().nextInt(1000);
        return milli + "-" + nextInt;
    }
}
