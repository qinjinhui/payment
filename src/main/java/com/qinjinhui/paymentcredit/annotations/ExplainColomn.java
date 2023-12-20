package com.qinjinhui.paymentcredit.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExplainColomn {
    /**
     *  字段名称
     */
    String column();
    /**
     *  字段解释
     */
    String explain();
    /**
     *  类型
     */
    String type() default "varchar";
    /**
     *  字段长度
     */
    String size() default "35";
    /**
     *  是否为空
     */
    boolean isNull() default true;
    /**
     *  是否主键
     */
    boolean isprimary() default false;
    /**
     *  表字段默认值
     */
    String columndefault() default "";
    /**
     *  是否默认值
     */
    boolean isdefault() default false;
}
