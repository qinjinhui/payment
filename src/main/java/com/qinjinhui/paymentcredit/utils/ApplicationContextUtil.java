package com.qinjinhui.paymentcredit.utils;

import com.qinjinhui.paymentcredit.dao.PayAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author qinjinhui
 * @Date 2022/12/3
 * @Describe  springboot手动注入
 **/
@Component
@Slf4j
public class ApplicationContextUtil implements ApplicationContextAware {

    @Autowired
    private  static   ApplicationContext context;

    /**
     * 获取上下文参数
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 根据name返回指定的bean
     * @param name
     * @return
     */
    public  static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     * 根据class类名返回执行的bean
     * @param clazz
     * @param <T>
     * @return
     */
    Map<Object,PayAccount> accountMap=new HashMap();
    public  static <T> T getBean(Class<T> clazz){
        Map<String, PayAccount> beansOfType = getApplicationContext().getBeansOfType(PayAccount.class);
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 根据name，class返回执行的bean
     * @param name
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> Optional<T> getBean(String name, Class<T> tClass){
        return Optional.of(getApplicationContext().getBean(name,tClass));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("手动注入application");
        this.context=applicationContext;
    }

}
