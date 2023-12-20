package com.qinjinhui.paymentcredit.core.infa;


/**
 * @Author qinjinhui
 * @Date 2022/9/29
 * @Describe
 **/
public interface ColumnTypeInfa {

    String columnType();

    void businessColumnType(StringBuffer stringBufferValue, Object value);
}
