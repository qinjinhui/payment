package com.qinjinhui.paymentcredit.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author qinjinhui
 * @Date 2022/9/23
 * @Describe  基础公共
 **/
@Mapper
@Repository
public interface UpdateMapper<T> {
    /**
     *
     * @param baseUpdate
     * @return
     */
    @Transactional
    int baseUpdate(@Param(value = "baseUpdate") Object baseUpdate);


}

