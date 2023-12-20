package com.qinjinhui.paymentcredit.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**@Component 也可以用这个 万能的*/
@Mapper
@Repository
public interface TableMapper {

    /**
     * 创建数据库表
     *
     * @param tableName 表名称
     */
    void createTable(@Param("tableName") String tableName, @Param("creditSql") String creditSql, @Param("tableExplain") String tableExplain, @Param("isPrimary") String isPrimary);

}

