package com.qinjinhui.paymentcredit.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @Author qinjinhui
 * @Date 2023/12/29
 * @Describe  工厂抽象注册器
 **/
@Slf4j //此注解后续不使用  底层不使用任何三方插件
public abstract class AbstractBusinessHandler<T,S> implements CommonBusinessHandler<T,S>, ApplicationContextAware, InitializingBean, SmartInitializingSingleton, ApplicationListener<ContextRefreshedEvent> {


    protected  ApplicationContext applicationContext;

    @Override
    public Integer order() {
        return 0;
    }


    protected  abstract List<BusinessType> businessTypeList();

    @Override
    public int compareTo(CommonBusinessHandler o) {
        return order().compareTo(o.order());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Initializing bean: {}", this.getClass().getSimpleName());
        log.info("businessTypeList() result: {}", businessTypeList());
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            businessTypeList().forEach(this::init);
        });
        future.join();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Initializing onApplicationEvent: {}");
    }

    @Override
    public void afterSingletonsInstantiated() {
        log.info("Initializing afterSingletonsInstantiated: {}");
    }



    private void  init(BusinessType type) {
        CommonBusinessHandler proxyObject = getProxyObject();
        CommonBusinessFactory.register(type,proxyObject);
    }



    private CommonBusinessHandler getProxyObject() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            log.info(beanName);
        }

        Map<String, ? extends CommonBusinessHandler> beanMap = applicationContext.getBeansOfType(this.getClass());
        if (CollectionUtils.isEmpty(beanMap)) {
            throw new NoSuchBeanDefinitionException(this.getClass());
        }
        if (beanMap.size() == 1) {
            return beanMap.values().iterator().next();
        }
        for (CommonBusinessHandler proxyObj : beanMap.values()) {
            if (this.getClass().equals(AopUtils.getTargetClass(proxyObj))) {
                return proxyObj;
            }
        }
        throw new NoSuchBeanDefinitionException(this.getClass());
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
