package com.qinjinhui.paymentcredit;

import com.alipay.sofa.runtime.api.annotation.SofaAsyncInit;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient//开启erueka客户端
@EnableApolloConfig //开启apollo客户端
@SofaAsyncInit
public class PaymentCreditApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentCreditApplication.class, args);
    }



}
