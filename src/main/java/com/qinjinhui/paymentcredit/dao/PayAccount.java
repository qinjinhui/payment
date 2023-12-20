package com.qinjinhui.paymentcredit.dao;



import com.qinjinhui.paymentcredit.annotations.ExplainColomn;
import com.qinjinhui.paymentcredit.annotations.ExplainTable;
import com.qinjinhui.paymentcredit.validation.annotations.NotEmpty;
import lombok.Builder;
import lombok.Data;

/**
 * 支付账户
 * @author Administrator
 */
@Data
@Builder
@ExplainTable(value = "支付账户",name = "pay_account")
public class PayAccount {


	/**
	 * 编号
	 */
	@ExplainColomn(column = "id",explain = "编号",isprimary = true,type = "int",isNull = false)
	@NotEmpty(paraphrase ="1 ",describe = "2")
	public int id;
	
	/**
	 * 用户支付编号
	 */
	@ExplainColomn(column = "payid",explain = "用户支付编号")
	public String payid;
	
	/**
	 * app类型
	 */
	@ExplainColomn(column = "apptype",explain = "app类型")
	public String apptype;
	/**
	 * 支付类型，微信0，支付宝1，银联2
	 */
	@ExplainColomn(column = "accounttype",explain = "支付类型，微信0，支付宝1，银联2",columndefault = "0",isdefault = true)
	private String accounttype;
	/**
	 * 卡号
	 */
	@ExplainColomn(column = "cardid",explain = "卡号")
	private String cardid;
	/**
	 * 银行卡的类型
	 */
	@ExplainColomn(column = "cardidtype",explain = "银行卡的类型")
	private String cardidtype;
	/**
	 * 发卡行
	 */
	@ExplainColomn(column = "issuer",explain = "发卡行")
	private String issuer;
	/**
	 * 敏感信息
	 */
	@ExplainColomn(column = "info",explain = "敏感信息")
	private String info;
	/**
	 * 电话号码
	 */
	@ExplainColomn(column = "phoneno",explain = "电话号码")
	private String phoneno;
	/**
	 * 状态
	 */
	@ExplainColomn(column = "status",explain = "状态")
	private String status;
	/**
	 * 创建时间
	 */
	@ExplainColomn(column = "createtime",explain = "创建时间")
	private Long createtime;
	/**
	 * 更新时间
	 */
	@ExplainColomn(column = "updatetime",explain = "更新时间",type = "date")
	private String updatetime;
	
	/**
	 * 签约状态
	 * @param from
	 */
	@ExplainColomn(column = "signstate",explain = "签约状态")
	private String signstate;

	public void setAccounttype(String s, String s1) {

	}


}
