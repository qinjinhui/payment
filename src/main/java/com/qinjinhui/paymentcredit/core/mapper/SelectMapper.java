package com.qinjinhui.paymentcredit.core.mapper;

import com.qinjinhui.paymentcredit.core.provider.BaseSelectProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author qinjinhui
 * @Date 2022/9/23
 * @Describe  基础公共
 **/
@Mapper
@Repository
public interface SelectMapper<T> {
    /**
     *
     * @return
     */
    @SelectProvider(type = BaseSelectProvider.class,method = "selectAll")
    List<T> baseSelect(Object record);

}

