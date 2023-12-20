package com.qinjinhui.paymentcredit.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyDefault {
    /**
     *  释义
     */
    String paraphrase();
    /**
     *  ，描述
     */
    String describe();
}
