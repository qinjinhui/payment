package com.qinjinhui.paymentcredit.externalserver;

import com.qinjinhui.paymentcredit.core.dao.PaymentExecuteConfigImpl;
import com.qinjinhui.paymentcredit.dao.PaymentExecuteConfig;
import com.qinjinhui.paymentcredit.iserver.IPaymentServer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author qinjinhui
 * @Date 2022/12/18
 * @Describe
 **/
public abstract class AbstractPaymentServer {


    @Autowired
    PaymentServerFactory paymentServerFactory;

    @Autowired
    PaymentExecuteConfigImpl paymentExecuteConfigDao;


    public void init(){
        //单独参数校验

        //构建订单

        //参数初始化
        IPaymentServer paymentServer = paymentServerFactory.getPaymentServer(paymentType);
        if (null == paymentServer)
        {
            return;
        }
        paymentServer.business();
    }

    //设置参数
    public String paymentType;

    public boolean checkPayment(){
        List<PaymentExecuteConfig> paymentExecuteConfigs = paymentExecuteConfigDao.getMapper().baseSelect(PaymentExecuteConfig.class);
        for (PaymentExecuteConfig paymentExecuteConfig : paymentExecuteConfigs) {
            String executeContent = paymentExecuteConfig.executeContent;
            //反射类执行
        }
        return true;
    }

}
