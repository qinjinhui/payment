package com.qinjinhui.paymentcredit.core.service;

import com.qinjinhui.paymentcredit.core.enumA.ColumnTypeEnum;
import com.qinjinhui.paymentcredit.core.infa.ColumnTypeInfa;
import org.springframework.stereotype.Service;

/**
 * @Author qinjinhui
 * @Date 2022/9/29
 * @Describe
 **/
@Service
public class ColumnTypeIntImpl implements ColumnTypeInfa {
    @Override
    public String columnType() {
        return ColumnTypeEnum.INT.getCode();
    }

    @Override
    public void businessColumnType(StringBuffer stringBufferValue,Object value) {
        stringBufferValue.append(value);
    }
}
