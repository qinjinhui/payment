package com.qinjinhui.paymentcredit.mapper;

import com.qinjinhui.paymentcredit.core.mapper.CommonMapper;
import com.qinjinhui.paymentcredit.dao.PayAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface PayMapper extends CommonMapper<PayAccount> {

}

