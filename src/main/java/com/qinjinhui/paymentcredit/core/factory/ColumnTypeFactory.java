package com.qinjinhui.paymentcredit.core.factory;

import com.qinjinhui.paymentcredit.core.infa.ColumnTypeInfa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author qinjinhui
 * @Date 2022/9/29
 * @Describe
 **/
@Slf4j
@Component
public class ColumnTypeFactory {

    Map<String, ColumnTypeInfa> typeInfaMap ;

    @Autowired
    List<ColumnTypeInfa> columnTypeInfas;


    public ColumnTypeInfa columnTypeBuild(String columnType){
        if (null==typeInfaMap){
            generateColumnListFactory();
        }
        ColumnTypeInfa columnTypeInfa = typeInfaMap.get(columnType);
        return columnTypeInfa;
    }
    public void generateColumnListFactory(){
        //给出key重复时，使用哪个key作为主键，以下代码中的(key1, key2) -> key2)代表key1和key2键重复时返回key2做主键
        typeInfaMap= columnTypeInfas.stream().collect(Collectors.toMap(ColumnTypeInfa::columnType, Function.identity(), (key1, key2) -> key2));
    }


}
