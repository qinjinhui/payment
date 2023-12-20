package com.qinjinhui.paymentcredit.core.dao;
import java.util.List;
/**
 * @Author qinjinhui
 * @Date 2022/9/23
 * @Describe
 **/
public interface IBaseDao<T> {

    /**
     *  插入数据
     * @param record
     * @return 插入总条数
     */
    int baseInsert(T record);

    /**
     *更新数据
     * @param record
     * @return 更新总条数
     */
    int baseUpdate(T record);

}

