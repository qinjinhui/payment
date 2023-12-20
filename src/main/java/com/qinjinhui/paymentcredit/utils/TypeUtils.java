package com.qinjinhui.paymentcredit.utils;


import cn.hutool.core.util.ReflectUtil;
import com.qinjinhui.paymentcredit.annotations.ExplainColomn;
import com.qinjinhui.paymentcredit.annotations.ExplainTable;
import com.qinjinhui.paymentcredit.core.factory.ColumnTypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @Author qinjinhui
 * @Date 2022/9/26
 * @Describe
 **/
@Slf4j
@Component
public class TypeUtils {

    public final static String INSERT_SQL = "INSERT INTO ";
    public final static String INSERT_SQL_PARENTHESES_LEFT = "(";
    public final static String INSERT_SQL_PARENTHESES_RIGHT = ")";
    public final static String INSERT_SQL_VALUES = " VALUES ";

    public final static String UPDATE_SQL = "UPDATE ";
    public final static String UPDATE_SQL_SET = " SET ";
    public final static String MYSQL_WHERE = " WHERE ";

    public final static String SELECT_SQL = "SELECT * FROM ";

    @Autowired
    ColumnTypeFactory columnTypeFactory;


    public String assemblyInsertSql(Object insertClassName) {
        log.info("=======================生成SQL开始=======================");
        StringBuffer stringBufferKey = new StringBuffer();
        StringBuffer stringBufferValue = new StringBuffer();
        ExplainTable exAnnotation = (ExplainTable) insertClassName.getClass().getAnnotation(ExplainTable.class);
        Arrays.asList(ReflectUtil.getFields(insertClassName.getClass())).forEach(insertClass -> {
            ExplainColomn explainColomn = insertClass.getAnnotation(ExplainColomn.class);
            insertClass.setAccessible(true);
            stringBufferKey.append(explainColomn.column());
            stringBufferKey.append(",");
            try {
                if (!StringUtils.isEmpty(insertClass.get(insertClassName))) {
                    columnTypeFactory.columnTypeBuild(explainColomn.type()).
                            businessColumnType(stringBufferValue, insertClass.get(insertClassName));
                } else {
                    stringBufferValue.append(" ");
                }
                stringBufferValue.append(",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        String insertSql = INSERT_SQL + exAnnotation.name() + INSERT_SQL_PARENTHESES_LEFT + stringBufferKey.substring(0, stringBufferKey.length() - 1) +
                INSERT_SQL_PARENTHESES_RIGHT + INSERT_SQL_VALUES + INSERT_SQL_PARENTHESES_LEFT + stringBufferValue.substring(0, stringBufferValue.length() - 1)
                + INSERT_SQL_PARENTHESES_RIGHT;
        log.info("生成Sql如下{}" + insertSql);
        log.info("=======================生成SQL结束=======================");
        return insertSql;
    }

    public String assemblyUpdateSql(Object insertClassName) {
        log.info("=======================生成UPDATE SQL开始=======================");
        StringBuffer stringBufferKey = new StringBuffer();
        StringBuffer stringBufferValue = new StringBuffer();
        ExplainTable exAnnotation = (ExplainTable) insertClassName.getClass().getAnnotation(ExplainTable.class);
        Arrays.asList(ReflectUtil.getFields(insertClassName.getClass())).forEach(insertClass -> {
            ExplainColomn explainColomn = insertClass.getAnnotation(ExplainColomn.class);
            insertClass.setAccessible(true);
            try {
                    if (!StringUtils.isEmpty(insertClass.get(insertClassName))) {
                        if (explainColomn.isprimary()){
                            stringBufferKey.append(explainColomn.column());
                            stringBufferKey.append("=");
                            columnTypeFactory.columnTypeBuild(explainColomn.type()).
                                    businessColumnType(stringBufferKey, insertClass.get(insertClassName));
                            stringBufferKey.append(" and ");
                        }else{
                            stringBufferValue.append(explainColomn.column());
                            stringBufferValue.append("=");
                            columnTypeFactory.columnTypeBuild(explainColomn.type()).
                                    businessColumnType(stringBufferValue, insertClass.get(insertClassName));
                            stringBufferValue.append(",");
                        }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        stringBufferKey.append("1=1");
        String insertSql = UPDATE_SQL + exAnnotation.name() + UPDATE_SQL_SET + stringBufferValue.substring(0, stringBufferValue.length() - 1)
                + MYSQL_WHERE+stringBufferKey.toString();
        log.info("生成UPDATE Sql如下{}" + insertSql);
        log.info("=======================生成UPDATE SQL结束=======================");
        return insertSql;
    }

    public String assemblySelectSql(Object insertClassName) {
        log.info("=======================生成Select SQL开始=======================");
        StringBuffer stringBufferKey = new StringBuffer();
        StringBuffer stringBufferValue = new StringBuffer();
        ExplainTable exAnnotation = (ExplainTable) insertClassName.getClass().getAnnotation(ExplainTable.class);
        Arrays.asList(ReflectUtil.getFields(insertClassName.getClass())).forEach(insertClass -> {
            ExplainColomn explainColomn = insertClass.getAnnotation(ExplainColomn.class);
            insertClass.setAccessible(true);
            try {
                if (explainColomn.isprimary()){
                    stringBufferKey.append(explainColomn.column());
                    stringBufferKey.append("=");
                    columnTypeFactory.columnTypeBuild(explainColomn.type()).
                            businessColumnType(stringBufferKey, insertClass.get(insertClassName));
                    stringBufferKey.append(" and ");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        stringBufferKey.append("1=1");
        String insertSql = SELECT_SQL + exAnnotation.name() +  MYSQL_WHERE+ stringBufferKey.toString();
        log.info("生成Select Sql如下{}" + insertSql);
        log.info("=======================生成Select SQL结束=======================");
        return insertSql;
    }

    public static String assemblySelectSqlAll(Object insertClassName) {
        log.info("=======================生成Select SQL开始=======================");
        StringBuffer stringBufferKey = new StringBuffer();
        ExplainTable exAnnotation = (ExplainTable) insertClassName.getClass().getAnnotation(ExplainTable.class);
        stringBufferKey.append("1=1");
        String insertSql = SELECT_SQL + exAnnotation.name() +  MYSQL_WHERE+ stringBufferKey.toString();
        log.info("生成Select Sql如下{}" + insertSql);
        log.info("=======================生成Select SQL结束=======================");
        return insertSql;
    }
}
