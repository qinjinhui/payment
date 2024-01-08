package com.qinjinhui.paymentcredit.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Author qinjinhui
 * @Date 2024/1/8
 * @Describe 线程池
 *
 **/
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean(name = "fileImportTaskExecutor")
    public Executor fileImportTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 设置核心线程数
        executor.setMaxPoolSize(20); // 设置最大线程数
        executor.setQueueCapacity(500); // 设置队列容量
        executor.setThreadNamePrefix("file-import-thread-"); // 设置线程名前缀
        executor.initialize();
        return executor;
    }
}
