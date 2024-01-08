package com.qinjinhui.paymentcredit.controller;


import com.qinjinhui.paymentcredit.core.baseFrom.BaseFrom;
import com.qinjinhui.paymentcredit.factory.BusinessType;
import com.qinjinhui.paymentcredit.factory.CommonBusinessFactory;
import com.qinjinhui.paymentcredit.factory.EnumFactoryType;
import com.qinjinhui.paymentcredit.login.PassWordFrom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/v1/aaa")
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/v1/login")
    public void ControllerTest(PassWordFrom passWordFrom){
        CommonBusinessFactory.executeSign(
                new BusinessType(EnumFactoryType.STANDARD_LOGIN.getCode(), passWordFrom.getLoginMethod().getCode()),passWordFrom);
        LOGGER.info("访问Controlller-----");
    }
}
