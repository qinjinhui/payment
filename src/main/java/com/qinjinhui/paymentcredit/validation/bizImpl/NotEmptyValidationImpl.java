package com.qinjinhui.paymentcredit.validation.bizImpl;

import com.qinjinhui.paymentcredit.validation.annotations.NotEmpty;
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
public class NotEmptyValidationImpl implements ValidationInte {
    @Override
    public Class<? extends Annotation> getVaLidation() {
        return NotEmpty.class;
    }

    @Override
    public boolean getBasic(Object checkColum) {
        if (null==checkColum){
            log.error("参数不能为空");
            return false;
        }
        log.info("============================NotEmptyValidationImpl");
        return true;
    }
}
