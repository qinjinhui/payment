package com.qinjinhui.paymentcredit;

import com.qinjinhui.paymentcredit.login.LoginServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author qinjinhui
 * @Date 2024/1/10
 * @Describe
 **/
@Configuration
@ComponentScan(basePackages = {"com.qinjinhui.paymentcredit", "com.qinjinhui.paymentcredit.login"})
public class AppConfig {

//    @Bean
//    public LoginServiceImpl loginService() {
//        return new LoginServiceImpl();
//    }
}
