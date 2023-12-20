package com.qinjinhui.paymentcredit.validation.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {
    /**
     *  释义
     */
    String paraphrase();
    /**
     *  ，描述
     */
    String describe();
}
