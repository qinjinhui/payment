package com.qinjinhui.paymentcredit.dao;



import com.qinjinhui.paymentcredit.annotations.ExplainColomn;
import com.qinjinhui.paymentcredit.annotations.ExplainTable;
import com.qinjinhui.paymentcredit.validation.annotations.NotEmpty;
import lombok.Builder;
import lombok.Data;

/**
 * 配置表
 * @author Administrator
 */
@Data
@Builder
@ExplainTable(value = "配置表",name = "payment_execute_config")
public class PaymentExecuteConfig {
	/**
	 * 编号
	 */
	@ExplainColomn(column = "id",explain = "编号",isprimary = true,type = "int",isNull = false)
	@NotEmpty(paraphrase ="1 ",describe = "2")
	public int id;

	@ExplainColomn(column = "execute_type",explain = "执行类型",isNull = false)
	public String executeType;

	@ExplainColomn(column = "execute_content",explain = "执行内容",isNull = false)
	public String executeContent;


}
