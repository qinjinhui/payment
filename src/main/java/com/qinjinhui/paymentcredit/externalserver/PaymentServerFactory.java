package com.qinjinhui.paymentcredit.externalserver;

import com.qinjinhui.paymentcredit.iserver.IPaymentServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author qinjinhui
 * @Date 2022/12/18
 * @Describe   支付工厂类1
 **/
@Component
@Slf4j
public class PaymentServerFactory implements ApplicationContextAware {

    static Map<String, IPaymentServer> classValidationInteMaps = new HashMap<>();

    public IPaymentServer   getPaymentServer(String paymentType){
        return classValidationInteMaps.get(paymentType);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        try {
            classValidationInteMaps = applicationContext
                    .getBeansOfType(IPaymentServer.class)
                    .values()
                    .stream()
                    .collect(Collectors.toList())
                    .stream()
                    .collect(Collectors.toMap(IPaymentServer::getPaymentType, v -> v, (p1, p2) -> p1));
        }catch (BeansException e)
        {
            log.error("======"+e);
            log.error("");
        }



    }
}
