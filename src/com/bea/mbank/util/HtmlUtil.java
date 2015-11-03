package com.bea.mbank.util;

import com.bea.database.DbManager;
import com.bea.remote.models.grwdy.ApplyLoanInforBO;
import com.bea.remote.models.grwdy.BasicInfoBO;
import com.bea.remote.models.grwdy.BorrowerInforBO;
import com.bea.remote.models.grwdy.ContactInforBO;
import com.bea.remote.models.grwdy.JointInforBO;
import com.bea.remote.models.grwdy.ProfessionInforBO;
import com.bea.remote.models.grwdy.MateInforBO;

/**
 * @author fanglinhao
 * 个人无抵押预览生成html
 */
public class HtmlUtil {
	boolean tdFlag = true;
	/**
	 * 个人资料
	 * @param info
	 * @return
	 */
	public String borrowerInforBOHtml(BorrowerInforBO info){
		if(info==null)
			return "";
		StringBuilder pageHtml = new StringBuilder();
		if(null != info.getMFName() && null != info.getMLName())
			pageHtml.append(putTr("中文名",info.getMFName()+" "+info.getMLName()));
		if(null != info.getMEFName() && null != info.getMELName())
			pageHtml.append(putTr("英文名",info.getMEFName()+" "+info.getMELName()));
			pageHtml.append(putTr("性别",getSex(info.getMSex())));
			pageHtml.append(putTr("国籍",DbManager.codeDataName("CountryCode", info.getMCountry())));
			pageHtml.append(putTr("出生日期",info.getMBDate()));
			pageHtml.append(putTr("证件类型",info.getMCTypeName()));
			pageHtml.append(putTr("证件号码",info.getMCNum()));
			pageHtml.append(putTr("发证国家",DbManager.codeDataName("CountryCode", info.getMCC())));
			pageHtml.append(putTr("证件到期日",info.getMCDate()));
			pageHtml.append(putTr("同意交叉营销",isorno(info.getMSale())));
			pageHtml.append(putTr("业务来往情况",info.getMLRDTName()));
			pageHtml.append(putTr("客户源",info.getMCSName()));
			pageHtml.append(putSingleTr("银行通知方式",DbManager.codeDataName("MessageReceiveType", info.getMBRType())));
			pageHtml.append(putTr("电子邮箱",info.getMEmail()));
			pageHtml.append(putTr("账单投递方式","纸质对账单"));
			
			pageHtml.append(putTr("通讯地址",getTxAddr(info.homeAddr.getMRAddr())));
			pageHtml.append(putTr("国家",info.homeAddr.getMACountryName()));
			pageHtml.append(putSingleTr("省/市/区",info.homeAddr.getMACityName()));
			pageHtml.append(putSingleTr("住宅地址",formatString(info.homeAddr.getMAD3()) + " " + formatString(info.homeAddr.getMAD4())));
			pageHtml.append(putTr("邮政编码",info.homeAddr.getMPC()));
			pageHtml.append(putTr("邮寄方式",info.homeAddr.getMSTypeName()));
			
			pageHtml.append(putTr("国际区号",info.getMIACode()));
			pageHtml.append(putTr("电话号码",info.getMTNum()));
			pageHtml.append(putTr("是否接收短信",isorno(info.getMRS())));
			pageHtml.append(putTr("教育水平",DbManager.codeDataName("EducationExperience", info.getMEE())));
			pageHtml.append(putTr("婚姻状况",DbManager.codeDataName("Marriage", info.getMMType())));
			pageHtml.append(putTr("月收入金额",null != info.getMMoney()?info.getMMoney()+" 元":""));
			pageHtml.append(putTr("是否为东亚银行董事/雇员之亲属","否"));
			
			if(!tdFlag)
				pageHtml.append("</tr>");
			
		return pageHtml.toString();
	}
	
	/**
	 * 职业资料
	 * @param info
	 * @return
	 */
	public String professionInforBOHtml(ProfessionInforBO info){
		if(info==null)
			return "";
		tdFlag = true;
		StringBuilder pageHtml = new StringBuilder();
		pageHtml.append(putTr("是否本行员工",isorno(info.getJBEA())));
		pageHtml.append(putTr("员工所在部门",info.getJBEAD()));
		pageHtml.append(putTr("员工工号",info.getJBEAID()));
		pageHtml.append(putTr("雇佣类型",DbManager.codeDataName("EmploymentType", info.getJET())));
		pageHtml.append(putTr("注册币种",info.getJCurrencyName()));
		pageHtml.append(putTr("注册资金",addYuan(info.getJCapital())));
		pageHtml.append(putSingleTr("单位名称",info.getJCName()));
		pageHtml.append(putTr("国家",info.compAddres.getMACountryName()));
		pageHtml.append(putTr("省/市/区",info.compAddres.getMACityName()));
		pageHtml.append(putSingleTr("单位地址",formatString(info.compAddres.getMAD3())+formatString(info.compAddres.getMAD4())));
		pageHtml.append(putSingleTr("行业分类(中国)",info.getJITypeName()));
		pageHtml.append(putSingleTr("行业分类(香港)",info.getJITypeHKName()));
		pageHtml.append(putSingleTr("职业",DbManager.codeDataName("OccupationBEA", info.getJOBEA())));
		pageHtml.append(putSingleTr("职位",DbManager.codeDataName("Position", info.getJPosition())));
		pageHtml.append(putTr("入职日期",info.getJIDate()));
		pageHtml.append(putTr("工作年限",null == info.getJYear()?"":info.getJYear()+" 月"));
		
		if(!tdFlag)
			pageHtml.append("</tr>");
		
		return pageHtml.toString();
	}
	
	/**
	 * 生成html标签
	 * @param info 借款人配偶信息
	 * @param caption 标题
	 * @return
	 */
	public String mateInforBOHtml(MateInforBO info){
		if(info==null)
			return "";
		tdFlag = true;
		StringBuilder pageHtml = new StringBuilder();
		if(null != info.getWFName() && null != info.getWLName())
			pageHtml.append(putTr("中文名",info.getWFName()+" "+info.getWLName()));
		if(null != info.getWEFName() && null != info.getWELName())
			pageHtml.append(putTr("英文名",info.getWEFName()+" "+info.getWELName()));
		pageHtml.append(putTr("证件类型",DbManager.codeDataName("CertType", info.getWCType())));
		pageHtml.append(putTr("证件号码",info.getWCNum()));
		pageHtml.append(putTr("发证国家",DbManager.codeDataName("CountryCode", info.getWCC())));
		pageHtml.append(putTr("证件有效期",info.getWCDate()));
		
		if(!tdFlag)
			pageHtml.append("</tr>");
		
		return pageHtml.toString();
	}
	
	/**
	 * 生成html标签
	 * @param info联系人信息
	 * @param caption
	 * @return
	 */
	public String contactInforBOHtml(ContactInforBO info){
		if(info==null)
			return "";
		tdFlag = true;
		StringBuilder pageHtml = new StringBuilder();
		pageHtml.append(putTr("关系类型",info.getLRSName()));
		if(null != info.getLFName() && null != info.getLLName())
			pageHtml.append(putTr("中文名",info.getLFName()+" "+info.getLLName()));
		if(null != info.getLEFName() && null != info.getLELName())
			pageHtml.append(putTr("英文名",info.getLEFName()+" "+info.getLELName()));
		pageHtml.append(putTr("国际区号",info.getLIACode()));
		pageHtml.append(putTr("电话号码",info.getLTNum()));
		
		if(!tdFlag)
			pageHtml.append("</tr>");
		
		return pageHtml.toString();
	}
	
	/**
	 * 生成html标签
	 * @param info共同借款人信息
	 * @param caption
	 * @return
	 */
	public String jointInforBOHtml(JointInforBO info){
		if(info==null)
			return "";
		tdFlag = true;
		StringBuilder pageHtml = new StringBuilder();
		
		if(null != info.getCFName() && null != info.getCLName())
			pageHtml.append(putTr("中文名",info.getCFName()+" "+info.getCLName()));
		if(null != info.getCEFName() && null != info.getCELName())
			pageHtml.append(putTr("英文名",info.getCEFName()+" "+info.getCELName()));
		pageHtml.append(putTr("与借款人关系",DbManager.codeDataName("RelationShip", info.getCRelationship())));
		pageHtml.append(putTr("性别",getSex(info.getCSex())));
		pageHtml.append(putTr("国籍",DbManager.codeDataName("CountryCode", info.getCCountry())));
		pageHtml.append(putTr("出生日期",info.getCBDate()));
		pageHtml.append(putTr("证件类型",DbManager.codeDataName("CertType", info.getCCType())));
		pageHtml.append(putTr("证件号码",info.getCCNum()));
		pageHtml.append(putTr("发证国家",DbManager.codeDataName("CountryCode", info.getCCC())));
		pageHtml.append(putTr("证件到期日",info.getCCDate()));
		pageHtml.append(putTr("教育水平",DbManager.codeDataName("EducationExperience", info.getCEE())));
		pageHtml.append(putTr("婚姻状况",DbManager.codeDataName("Marriage", info.getCMType())));
		pageHtml.append(putTr("同意交叉营销",isorno(info.getCSale())));
		pageHtml.append(putTr("业务来往情况",info.getCLRDTName()));
		pageHtml.append(putTr("客户源",info.getCCSName()));
		pageHtml.append(putTr("银行通知方式",DbManager.codeDataName("BillReceiveType", info.getCBRType())));
		pageHtml.append(putSingleTr("账单投递方式",DbManager.codeDataName("MessageReceiveType", info.getCMRType())));
		pageHtml.append(putTr("电子邮箱",info.getCEmail()));
		pageHtml.append(putTr("通讯地址",getTxAddr(info.homeAddr.getCRAddr())));
		pageHtml.append(putTr("地址类型",info.homeAddr.getCAddrName()));
		pageHtml.append(putSingleTr("地址详情",formatString(info.homeAddr.getCAD3())+formatString(info.homeAddr.getCAD4())));
		pageHtml.append(putTr("邮寄方式",info.homeAddr.getCSTypeName()));
		pageHtml.append(putTr("国际区号",info.getCIACode()));
		pageHtml.append(putTr("电话号码",info.getCTNum()));
		pageHtml.append(putTr("是否短信接收",isorno(info.getCRS())));
		pageHtml.append(putTr("月收入金额",null == info.getCTMoney()?"":info.getCTMoney()+" 元"));
		
		pageHtml.append(putTr("雇佣类型",DbManager.codeDataName("EmploymentType", info.getCET())));
		pageHtml.append(putTr("经营实体类型",DbManager.codeDataName("OperateType", info.getCOT())));
		pageHtml.append(putTr("注册币种",info.getCRCurrencyName()));
		pageHtml.append(putTr("注册资金",null == info.getCCapital()?"":info.getCCapital()+" 元"));
		pageHtml.append(putTr("单位名称",info.getCCName()));
		pageHtml.append(putTr("单位性质",info.getCCTpyeName()));
		pageHtml.append(putTr("国家",info.getCCCountryName()));
		pageHtml.append(putSingleTr("省/市/区",info.getCCityName()));
		pageHtml.append(putSingleTr("单位地址",info.getCCAddr()));
		pageHtml.append(putSingleTr("行业分类(中国)",info.getCITypeName()));
		pageHtml.append(putSingleTr("行业分类(香港)",info.getCITypeHKName()));
		pageHtml.append(putSingleTr("职业",DbManager.codeDataName("OccupationBEA", info.getCOBEA())));
		pageHtml.append(putSingleTr("职位",DbManager.codeDataName("Position", info.getCPosition())));
		pageHtml.append(putTr("入职日期",info.getCIDate()));
		pageHtml.append(putTr("工作年限",null == info.getCYear()?"":info.getCYear()+" 月"));
		pageHtml.append(putTr("是否本行员工",isorno(info.getCBEA())));
		pageHtml.append(putTr("员工工号",info.getCBEAID()));
		pageHtml.append(putTr("员工所在部门",info.getCBEAD()));
		
		if(!tdFlag)
			pageHtml.append("</tr>");
		
		return pageHtml.toString();
	}
	
	/**
	 * 生成html标签
	 * @param info 申请贷款及费用信息
	 * @param caption
	 * @return
	 */
	public String applyLoanInforBOHtml(Boolean ispaxb, ApplyLoanInforBO info){
		if(info==null)
			return "";
		tdFlag = true;
		StringBuilder pageHtml = new StringBuilder();
		pageHtml.append(putTr("产品类型",getIBType(info.getIBType())));
		pageHtml.append(putTr("贷款金额",addYuan(info.getILMoney())));
		pageHtml.append(putTr("贷款期限",null == info.getILDate()?"":info.getILDate()+" 月"));
		pageHtml.append(putTr("贷款用途",DbManager.codeDataName("loanpurposeType", info.getILT())));
		pageHtml.append(putSingleTr("用途备注",info.getOcomment()));
		pageHtml.append(putTr("贷款支付方式",info.getIPMName()));
//		pageHtml.append(putTr("不接收贷款短信",isorno(info.getIMType())));
		pageHtml.append(putTr("经办人",info.getIDNumName()));
		pageHtml.append(putTr("利率类型",info.getIRTypeName()));
		pageHtml.append(putTr("浮动幅度",addBaiFenHao(info.getIFluctuate())));
		pageHtml.append(putTr("执行利率",addBaiFenHao(info.getIAR())));
		pageHtml.append(putTr("还款方式","等额本息"));
		pageHtml.append(putTr("还款周期","月"));
		if(ispaxb && null !=info.getIPM() && "1".equals(info.getIPM())){
			pageHtml.append(putTr("是否在线支付",isorno(info.getIOnline())));
			pageHtml.append(putTr("放款账户币种",info.getIGCurrencyName()));
			pageHtml.append(putTr("放款账户/卡号",info.getIGAccount()));
			pageHtml.append(putTr("放款账户名称",info.getIGName()));
			pageHtml.append(putTr("还款账户是否同放款账户",isorno(info.getIBG())));
			pageHtml.append(putTr("还款账户币种",info.getIBCurrencyName()));
			pageHtml.append(putTr("还款账户/卡号",info.getIBAccount()));
			pageHtml.append(putTr("还款账号户名",info.getIBName()));
			pageHtml.append(putTr("还款账户开户行代码",info.getIBBank()));
		}
		pageHtml.append(putTr("账户管理费率",addBaiFenHao(info.getIManagement())));
		pageHtml.append(putTr("缴费周期",iperiodFormat(info.getIPeriod())));
		if(!tdFlag)
			pageHtml.append("</tr>");
		
		return pageHtml.toString();
	}
	
	private String iperiodFormat(String s){
		if(null == s || "".equals(s))
			return "";
		if("1".equals(s))
			return "一次性";
		if("2".equals(s))
			return "按月";
		return "";
	}
	
	/**
	 * 生成html标签
	 * @param info 申请贷款及费用信息
	 * @param caption
	 * @return
	 */
	public String basicInfo(BasicInfoBO info){
		if(info==null)
			return "";
		tdFlag = true;
		StringBuilder pageHtml = new StringBuilder();
		pageHtml.append(putTr("中文名",formatString(info.getLCFName())+" "+formatString(info.getLCLName())));
		pageHtml.append(putTr("英文名",formatString(info.getLCEFName())+" "+formatString(info.getLCELName())));
		pageHtml.append(putTr("证件类型",DbManager.codeDataName("CertType", info.getLCType())));
		pageHtml.append(putTr("发证国家",DbManager.codeDataName("CountryCode", info.getLCC())));
		pageHtml.append(putTr("证件号码",info.getLCNum()));
		
		if(!tdFlag)
			pageHtml.append("</tr>");
		
		return pageHtml.toString();
	}
	
	private String putSingleTr(String title,String content){
		if(null == content || "".equals(content))
			return "";
		tdFlag = true;
		StringBuilder pageHtml = new StringBuilder();
		pageHtml
		.append("<tr>")
		.append("<td style=\"width:20%\">")
		.append("<span class=\"spantext\">"+title+"</span>")
		.append("</td>")
		.append("<td style=\"width:70%\" colspan=\"3\">")
		.append("<span class=\"spanvalue\">"+content+"</span>")
		.append("</td>")
		.append("</tr>");
		return pageHtml.toString();
	}
	
	/**
	 * 生成html标签
	 * @param title
	 * @param content
	 * @return
	 */
	private String putTr(String title,String content){
		if(null == content || "".equals(content))
			return "";
		StringBuilder pageHtml = new StringBuilder();
		if(tdFlag){
			pageHtml = pageHtml.append("<tr>");
		}
		
		pageHtml = pageHtml.append(putTd(title,content));
		
		if(tdFlag){
			pageHtml = pageHtml.append("</tr>");
		}
		
		return pageHtml.toString();
	}
	
	/**
	 * 生成html标签
	 * @param title
	 * @param content
	 * @return
	 */
	private String putTd(String title,String content){
		if(tdFlag){
			tdFlag = false;
		}else{
			tdFlag = true;
		}
		
		StringBuilder pageHtml = new StringBuilder();
		pageHtml
		.append("<td style=\"width:20%\">")
		.append("<span class=\"spantext\">"+title+"</span>")
		.append("</td>")
		.append("<td style=\"width:30%\">")
		.append("<span class=\"spanvalue\">"+content+"</span>")
		.append("</td>");
		return pageHtml.toString();
	}
	
	/**
	 * @param s
	 * @return
	 */
	private String isorno(String s){
		if(null == s || "".equals(s.trim()))
			return "是";
		if("1".equals(s)){
			return "是";
		}else{
			return "否";
		}
	}
	
	private String getSex(String s){
		if(null == s || "".equals(s.trim()))
			return "男";
		if("1".equals(s)){
			return "男";
		}else if("2".equals(s)){
			return "女";
		}else{
			return "其他";
		}
	}
	
	private String getIBType(String s){
		if(null == s)
			return "";
		if("0201".equals(s))
			return "个人无抵押消费贷款";
		if("0206".equals(s))
			return "个人无抵押车位贷款";
		if("0205".equals(s))
			return "平安信保消费贷";
		return "";
	}
	
	private String addBaiFenHao(String s){
		if(null == s || "".equals(s))
			return "";
		return s+" %";
	}
	
	//通讯地址
	private String getTxAddr(String s){
		if(null == s || "".equals(s))
			return "";
		if("1".equals(s))
			return "住宅地址";
		if("2".equals(s))
			return "单位地址";
		return "";
	}
	
	private String formatString(String s){
		if(null == s)
			return "";
		if("null".equals(s))
			return "";
		return s;
	}
	
	private String addYuan(String s){
		if(null == s || "".equals(s.trim()))
			return "";
		return s+" 元";
	}
}
