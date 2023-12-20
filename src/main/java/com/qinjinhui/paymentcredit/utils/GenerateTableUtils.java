package com.qinjinhui.paymentcredit.utils;


import com.qinjinhui.paymentcredit.annotations.ExplainColomn;
import com.qinjinhui.paymentcredit.annotations.ExplainTable;
import com.qinjinhui.paymentcredit.core.mapper.TableMapper;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Builder
@Component
public class GenerateTableUtils {

  private   final  static  String THE_BLANK_SPACE =" ";
  private   final  static  String MYSQL_COLUMN_SINGLE_QUOTES ="`";
    private   final  static  String MYSQL_DESCRIBE_SINGLE_QUOTES ="'";

  @Autowired
  TableMapper tableMapper;

    public void generateTable(Class<?>  className){
        List<String> sqlList=new ArrayList<>();
        AtomicReference<StringBuffer> buffer = new AtomicReference<>(new StringBuffer());
        log.info("===============generate生成{}表开始==============="+className);
        // 取得本类的全部属性
        ExplainTable exAnnotation = (ExplainTable) className.getAnnotation(ExplainTable.class);
        Arrays.asList(className.getDeclaredFields()).forEach(field -> {
            ExplainColomn annotation = field.getAnnotation(ExplainColomn.class);
            String isNull = annotation.isNull() ? "NULL" : " NOT NULL";
            String isPrimary = annotation.isprimary() ? "AUTO_INCREMENT COMMENT" : "COMMENT";
            String creditSql = buildCreditSql(annotation.column(), annotation.type(),
                    annotation.size(), annotation.explain(), isNull, isPrimary,annotation.columndefault(),annotation.isdefault());
            if (annotation.isprimary()){
                buffer.set(primaryBuildSql(buffer.get(), field.getName()));
            }
            sqlList.add(creditSql);
        });
        String createSql = createSql(sqlList);
        String primarySql = buffer.get().deleteCharAt(buffer.get().length() - 1).toString();
        String tableNameDescrebe = createTableName(exAnnotation.value());
        //生成表
        tableMapper.createTable(exAnnotation.name(),createSql,tableNameDescrebe,primarySql);
        log.info("===============generate生成{}表结束==============="+className);
    }

    private String buildCreditSql(String column,String type,String size,String explain,
                                  String isNull,String isprimary,String columDefault,boolean isDefault){
        StringBuffer buffer=new StringBuffer();
        buffer.append(MYSQL_COLUMN_SINGLE_QUOTES);
        buffer.append(column);
        buffer.append(MYSQL_COLUMN_SINGLE_QUOTES);
        buffer.append(THE_BLANK_SPACE);
        buffer.append(type);
        buffer.append(THE_BLANK_SPACE);
        buffer.append("("+size+")");
        buffer.append(THE_BLANK_SPACE);
        buffer.append(isNull);
        buffer.append(THE_BLANK_SPACE);
        buffer.append(isprimary);
        buffer.append(THE_BLANK_SPACE);
        buffer.append(MYSQL_DESCRIBE_SINGLE_QUOTES);
        buffer.append(explain);
        buffer.append(MYSQL_DESCRIBE_SINGLE_QUOTES);
        if (isDefault){
            buffer.append(THE_BLANK_SPACE);
            buffer.append("default");
            buffer.append(THE_BLANK_SPACE);
            buffer.append(MYSQL_DESCRIBE_SINGLE_QUOTES);
            buffer.append(columDefault);
            buffer.append(MYSQL_DESCRIBE_SINGLE_QUOTES);
        }
        buffer.append(",");
        return  buffer.toString();
    }

    private String createSql(List<String> sqlList)
    {
        return  sqlList.stream().collect(Collectors.joining());
    }
    private StringBuffer primaryBuildSql(StringBuffer buffer,String column){
        buffer.append(MYSQL_COLUMN_SINGLE_QUOTES);
        buffer.append(column);
        buffer.append(MYSQL_COLUMN_SINGLE_QUOTES);
        buffer.append(",");
        return buffer;
    }

    private String createTableName(String tableName){
        return MYSQL_DESCRIBE_SINGLE_QUOTES+tableName+MYSQL_DESCRIBE_SINGLE_QUOTES;
    }
}
