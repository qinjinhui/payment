package com.qinjinhui.paymentcredit.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @Author qinjinhui
 * @Date 2024/1/8
 * @Describe
 **/
@Configuration
public class ExecutorServiceConfig {

    @Bean
    public ExecutorService executorService() {
        // 这里可以根据实际需求调整线程池的配置
        int poolSize = Runtime.getRuntime().availableProcessors();
        return Executors.newFixedThreadPool(poolSize);
    }
}
