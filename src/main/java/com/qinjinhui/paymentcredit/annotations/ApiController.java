package com.qinjinhui.paymentcredit.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ApiController {
    /**
     *  表解释
     */
    String value() default "0";
    /**
     *  表名
     */
    String name() default "";
}
