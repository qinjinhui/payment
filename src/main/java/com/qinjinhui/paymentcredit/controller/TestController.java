package com.qinjinhui.paymentcredit.controller;


import com.qinjinhui.paymentcredit.core.baseFrom.BaseFrom;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/v1/aaa")
public class TestController implements testInfate{
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/v1/contro")
    public void ControllerTest(BaseFrom BaseFrom){
        LOGGER.info("访问Controlller-----");
    }

    @Override
    public String aa() {
        return "";
    }
}
