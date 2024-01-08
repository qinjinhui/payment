package com.qinjinhui.paymentcredit.login;

import com.qinjinhui.paymentcredit.factory.AbstractLoginFactorBuildHandler;
import org.springframework.stereotype.Service;

/**
 * @Author qinjinhui
 * @Date 2024/1/8
 * @Describe  密码登录实现类
 **/
@Service
public class PassWordLoginService extends AbstractLoginFactorBuildHandler<PassWordFrom,PassWordResult> {
    @Override
    public String tradIngCode() {
        return EnumLoginMethod.PASSWORD.getCode();
    }

    @Override
    public PassWordResult businessExecute(PassWordFrom request) {
        return null;
    }


}
