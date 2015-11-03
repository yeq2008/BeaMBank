package com.bea.mbank.util;

import xc.android.utils.XCToolkit;

public class SubHelperUtil extends HeplerUtil {
	private static SubHelperUtil Subhelperutil = null;
	private static String tableName="";
	
	public static SubHelperUtil getInstance() {
		if (Subhelperutil == null) {
			Subhelperutil = new SubHelperUtil();
		}
		return Subhelperutil;
	}

	public static void setTableName(String table){
		if(table!=null&&!table.equals(""))
			tableName=" @ "+table;
		else
			tableName="";
	}
	
	// 验证模糊字段的方法
	public static boolean showNormal(String field, String fieldname) {
		if (null == field || field.equals("")) {
			XCToolkit.showToast("请输入或者选择" + fieldname +tableName);
			return false;
		}

		return true;
	}
	
	// 验证码表信息的方法
	public static boolean showToast(String field, String fieldname) {
		if (null == field || field.equals("")) {
			XCToolkit.showToast("请选择" + fieldname+tableName);
			return false;
		}

		return true;
	}
	
	// 验证是否位数字
	public static boolean showNumToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("请输入" + fieldname+tableName);
			return false;
		} else if (null != field && !isNumeric(field)) {
			XCToolkit.showToast("请输入正确的" + fieldname+tableName);
			return false;
		}
		return true;
	}
	
	// 验证是否为特定数量的数字
	public static boolean showNumToast(String field, String fieldname,int x) {
		if ("".equals(field)) {
			XCToolkit.showToast("请输入" + fieldname+tableName);
			return false;
		} else if (null != field && !isNumeric(field,x)) {
			XCToolkit.showToast("请输入正确的" + x + "位" + fieldname+tableName);
			return false;
		}
		return true;
	}
	
	// 验证是否位小数
	public static boolean showNumDecimalToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("请输入" + fieldname+tableName);
			return false;
		} else if (null != field && !isNumericDecimal(field)) {
			XCToolkit.showToast("请输入正确的" + fieldname+tableName);
			return false;
		}
		return true;
	}
	
	// 验证是否位利率
	public static boolean showRateToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("请输入" + fieldname+tableName);
			return false;
		} else if (null != field && !isRate(field)) {
			XCToolkit.showToast("请输入正确的" + fieldname+tableName);
			return false;
		}
		return true;
	}
	
	// 验证中文
	public static boolean showCHToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("请输入" + fieldname+tableName);
			return false;
		} else if (null != field && !isChinese(field)) {
			XCToolkit.showToast("请输入" + fieldname+tableName);
			return false;
		}
		return true;
	}

	// 验证邮箱
	public static boolean showEmailToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("请输入" + fieldname+tableName);
			return false;
		} else if (null != field && !isEmail(field)) {
			XCToolkit.showToast("请输入正确的邮箱地址"+tableName);
			return false;
		}
		return true;
	}

	// 验证英文字符
	public static boolean showEngToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("请输入" + fieldname+tableName);
			return false;
		} else if (null != field && !isEngStr(field)) {
			XCToolkit.showToast("请输入" + fieldname+tableName);
			return false;
		}
		return true;
	}

	// 验证身份证号
	public static boolean showIDCardToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("请输入" + fieldname+tableName);
			return false;
		} else if (null != field && !isIndentity(field)) {
			XCToolkit.showToast("请输入正确的身份证号"+tableName);
			return false;
		}
		return true;
	}

	// 验证邮编
	public static boolean showPcodeToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("请输入" + fieldname+tableName);
			return false;
		} else if (null != field && !isPcode(field)) {
			XCToolkit.showToast("请输入正确的邮编"+tableName);
			return false;
		}
		return true;
	}

	// 验证电话号码
	public static boolean showPhoneToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("请输入" + fieldname+tableName);
			return false;
		} else if ((null != field&&!isPhoneNum(field))||11 != field.length()) {
			XCToolkit.showToast("请输入正确的电话号码"+tableName);
			return false;
		}
		return true;
	}
	
	// 验证身份证
	public static boolean showIDToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("请输入" + fieldname+tableName);
			return false;
		} else if (null != field && !CertiCodeCheck.isIdCard(field)) {
			XCToolkit.showToast("请输入正确的身份证号码"+tableName);
			return false;
		}
		return true;
	}
}
