package com.qinjinhui.paymentcredit.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author qinjinhui
 * @Date 2022/9/23
 * @Describe
 **/
@Mapper
@Repository
public interface CommonMapper <T> extends InsertMapper<T>,UpdateMapper<T>,SelectMapper<T> {


}

