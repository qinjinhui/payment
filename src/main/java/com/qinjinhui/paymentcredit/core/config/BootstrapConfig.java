package com.qinjinhui.paymentcredit.core.config;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

/**
 * @Author qinjinhui
 * @Date 2022/12/16
 * @Describe 加载多个yml
 **/
public class BootstrapConfig {


//    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        //初始化yml配置工厂
//        PropertySourcesPlaceholderConfigurer property = new PropertySourcesPlaceholderConfigurer();
//        YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
//        MutablePropertySources sources = new MutablePropertySources();
//        try {
//            List<PropertySource<?>> db = loader.load("db", new ClassPathResource("config/appprops.yml"));
//            sources.addLast(db.get(0));
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        property.setPropertySources(sources);
//        return property;
//    }
}
