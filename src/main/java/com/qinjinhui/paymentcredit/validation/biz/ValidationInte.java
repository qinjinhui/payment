package com.qinjinhui.paymentcredit.validation.biz;

import java.lang.annotation.Annotation;

/**
 * @Author qinjinhui
 * @Date 2022/11/17
 * @Describe
 **/
public interface ValidationInte {

    Class<? extends Annotation> getVaLidation();

    boolean getBasic(Object checkColum);
}
