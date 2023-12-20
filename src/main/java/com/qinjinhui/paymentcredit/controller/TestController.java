package com.qinjinhui.paymentcredit.controller;


import com.qinjinhui.paymentcredit.annotations.ApiController;
import com.qinjinhui.paymentcredit.core.baseFrom.BaseFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;


@Controller
@Slf4j
@RequestMapping(value = "/v1/aaa")
public class TestController implements testInfate{
//    private static Logger logger= Logger.getLogger(TestController.class);

    public void ControllerTest(BaseFrom BaseFrom){
        log.info("访问Controller");

    }

    @Override
    public String aa() {
        return null;
    }
}
