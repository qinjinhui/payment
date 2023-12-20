package com.qinjinhui.paymentcredit.core.provider;

import com.qinjinhui.paymentcredit.utils.TypeUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author qinjinhui
 * @Date 2022/9/23
 **/
@Slf4j
public class BaseSelectProvider<T> {



    public String selectByPriarmy(Object record){
        return "";
    }

    public String selectAll(T record){
        return TypeUtils.assemblySelectSqlAll(record);
    }


}
