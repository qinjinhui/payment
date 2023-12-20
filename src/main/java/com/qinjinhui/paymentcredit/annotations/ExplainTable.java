package com.qinjinhui.paymentcredit.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ExplainTable {
    /**
     *  表解釋
     */
    String value();
    /**
     *  表名
     */
    String name();
}
