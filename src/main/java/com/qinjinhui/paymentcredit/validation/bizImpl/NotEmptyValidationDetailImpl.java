package com.qinjinhui.paymentcredit.validation.bizImpl;

import com.qinjinhui.paymentcredit.validation.annotations.NotEmptyDefault;
import com.qinjinhui.paymentcredit.validation.biz.ValidationInte;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;

/**
 * @Author qinjinhui
 * @Date 2022/11/18
 * @Describe
 **/
@Slf4j
@Service
public class NotEmptyValidationDetailImpl implements ValidationInte {
    @Override
    public Class<? extends Annotation> getVaLidation() {
        return NotEmptyDefault.class;
    }

    @Override
    public boolean getBasic(Object checkColum) {
        log.info("============================默认执行");
        return false;
    }
}
