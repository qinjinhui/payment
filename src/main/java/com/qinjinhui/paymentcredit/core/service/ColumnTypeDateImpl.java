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
public class ColumnTypeDateImpl implements ColumnTypeInfa {
    private   final    String SQL_DESCRIBE_SINGLE_QUOTES ="'";
    @Override
    public String columnType() {
        return ColumnTypeEnum.DATE.getCode();
    }

    /**
     * 字段类型组装
     * @param stringBufferValue
     * @param value
     */
    @Override
    public void businessColumnType(StringBuffer stringBufferValue,Object value) {
        stringBufferValue.append(SQL_DESCRIBE_SINGLE_QUOTES);
        stringBufferValue.append(value);
        stringBufferValue.append(SQL_DESCRIBE_SINGLE_QUOTES);
    }
}
