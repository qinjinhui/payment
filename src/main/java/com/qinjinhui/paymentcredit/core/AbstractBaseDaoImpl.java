package com.qinjinhui.paymentcredit.core;

import com.qinjinhui.paymentcredit.core.dao.IBaseDao;
import com.qinjinhui.paymentcredit.core.mapper.CommonMapper;
import com.qinjinhui.paymentcredit.utils.TypeUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author qinjinhui
 * @Date 2022/9/23
 * @Describe
 **/
public abstract class AbstractBaseDaoImpl<T,D extends CommonMapper<T>> implements IBaseDao<T> {

    @Autowired
    private D  mapper;

    @Autowired
    TypeUtils typeUtils;
    @Override
    public int baseInsert(T record) {
        return mapper.baseInsert(typeUtils.assemblyInsertSql(record));
    }

    @Override
    public int baseUpdate(T record) {
        return mapper.baseInsert(typeUtils.assemblyUpdateSql(record));
    }



    public D  getMapper(){
        return this.mapper;
    }
}
