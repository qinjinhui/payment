package com.qinjinhui.paymentcredit.core.mapper;

import com.qinjinhui.paymentcredit.dao.PayAccount;
import com.qinjinhui.paymentcredit.dao.PaymentExecuteConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface PaymentExecuteConfigMapper extends CommonMapper<PaymentExecuteConfig> {

}

