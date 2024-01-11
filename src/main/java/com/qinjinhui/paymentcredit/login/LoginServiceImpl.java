package com.qinjinhui.paymentcredit.login;

import com.qinjinhui.paymentcredit.factory.AbstractLoginFactorBuildHandler;
import com.qinjinhui.paymentcredit.service.ExcelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author qinjinhui
 * @Date 2024/1/9
 * @Describe
 **/
@Component
public class LoginServiceImpl extends AbstractLoginFactorBuildHandler<PassWordFrom,PassWordResult> {

    @Override
    public String tradIngCode() {
        return EnumLoginMethod.PASSWORD.getCode();
    }

    @Override
    public PassWordResult businessExecute(PassWordFrom request) {
        return null;
    }

}
