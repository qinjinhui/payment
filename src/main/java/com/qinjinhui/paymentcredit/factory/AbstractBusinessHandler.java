package com.qinjinhui.paymentcredit.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Map;

/**
 * @Author qinjinhui
 * @Date 2023/12/29
 * @Describe  工厂抽象注册器
 **/
@Slf4j //此注解后续不使用  底层不使用任何三方插件
public abstract class AbstractBusinessHandler<T,S> implements CommonBusinessHandler<T,S>, ApplicationContextAware, InitializingBean {

    protected  ApplicationContext applicationContext;

    @Override
    public Integer order() {
        return null;
    }

    @Override
    public S execute(T request) {
        return null;
    }

    protected  abstract List<BusinessType> businessTypeList();

    @Override
    public int compareTo(CommonBusinessHandler o) {
        return 0;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        businessTypeList().forEach(this::init);
    }
    
    private void  init(BusinessType type) {
        CommonBusinessHandler proxyObject = getProxyObject();
        CommonBusinessFactory.register(type,proxyObject);
    }
    
    private CommonBusinessHandler getProxyObject() {
        Map<String, ? extends CommonBusinessHandler> beanMap = applicationContext.getBeansOfType(this.getClass());
        if (CollectionUtils.isEmpty(beanMap)){
            //抛异常
            return  null;
        }
        if (beanMap.size() == 1) {
            return beanMap.values().iterator().next();
        }
        for (CommonBusinessHandler proxyObj : beanMap.values()) {
            if (this.getClass().equals(AopUtils.getTargetClass(proxyObj))) {
                return proxyObj;
            }
        }
        throw  new NoSuchBeanDefinitionException(this.getClass());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
