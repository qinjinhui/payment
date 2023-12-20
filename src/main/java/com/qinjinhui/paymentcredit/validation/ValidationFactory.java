package com.qinjinhui.paymentcredit.validation;

import com.qinjinhui.paymentcredit.validation.biz.ValidationInte;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author qinjinhui
 * @Date 2022/11/17
 * @Describe
 **/
@Component
@Slf4j
public class ValidationFactory implements ApplicationContextAware {


    @Autowired
    @Qualifier("notEmptyValidationDetailImpl")
    ValidationInte validationInteDetail;

    static Map<Class<? extends Annotation>, ValidationInte> classValidationInteMaps = new HashMap<>();

    public ValidationInte getValidationInteList(Class<? extends Annotation> validation) {
        if (validation.isAssignableFrom(validation)) {
            return classValidationInteMaps.get(validation);
        }
        return validationInteDetail;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        try {
            classValidationInteMaps = applicationContext
                    .getBeansOfType(ValidationInte.class)
                    .values()
                    .stream()
                    .collect(Collectors.toList())
                    .stream()
                    .collect(Collectors.toMap(ValidationInte::getVaLidation, v -> v, (p1, p2) -> p1));
        }catch (BeansException e)
        {
            log.error("======"+e);
            log.error("");
        }

    }
}
