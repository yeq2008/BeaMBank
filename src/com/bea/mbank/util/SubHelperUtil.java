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
	
	// ��֤ģ���ֶεķ���
	public static boolean showNormal(String field, String fieldname) {
		if (null == field || field.equals("")) {
			XCToolkit.showToast("���������ѡ��" + fieldname +tableName);
			return false;
		}

		return true;
	}
	
	// ��֤�����Ϣ�ķ���
	public static boolean showToast(String field, String fieldname) {
		if (null == field || field.equals("")) {
			XCToolkit.showToast("��ѡ��" + fieldname+tableName);
			return false;
		}

		return true;
	}
	
	// ��֤�Ƿ�λ����
	public static boolean showNumToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("������" + fieldname+tableName);
			return false;
		} else if (null != field && !isNumeric(field)) {
			XCToolkit.showToast("��������ȷ��" + fieldname+tableName);
			return false;
		}
		return true;
	}
	
	// ��֤�Ƿ�Ϊ�ض�����������
	public static boolean showNumToast(String field, String fieldname,int x) {
		if ("".equals(field)) {
			XCToolkit.showToast("������" + fieldname+tableName);
			return false;
		} else if (null != field && !isNumeric(field,x)) {
			XCToolkit.showToast("��������ȷ��" + x + "λ" + fieldname+tableName);
			return false;
		}
		return true;
	}
	
	// ��֤�Ƿ�λС��
	public static boolean showNumDecimalToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("������" + fieldname+tableName);
			return false;
		} else if (null != field && !isNumericDecimal(field)) {
			XCToolkit.showToast("��������ȷ��" + fieldname+tableName);
			return false;
		}
		return true;
	}
	
	// ��֤�Ƿ�λ����
	public static boolean showRateToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("������" + fieldname+tableName);
			return false;
		} else if (null != field && !isRate(field)) {
			XCToolkit.showToast("��������ȷ��" + fieldname+tableName);
			return false;
		}
		return true;
	}
	
	// ��֤����
	public static boolean showCHToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("������" + fieldname+tableName);
			return false;
		} else if (null != field && !isChinese(field)) {
			XCToolkit.showToast("������" + fieldname+tableName);
			return false;
		}
		return true;
	}

	// ��֤����
	public static boolean showEmailToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("������" + fieldname+tableName);
			return false;
		} else if (null != field && !isEmail(field)) {
			XCToolkit.showToast("��������ȷ�������ַ"+tableName);
			return false;
		}
		return true;
	}

	// ��֤Ӣ���ַ�
	public static boolean showEngToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("������" + fieldname+tableName);
			return false;
		} else if (null != field && !isEngStr(field)) {
			XCToolkit.showToast("������" + fieldname+tableName);
			return false;
		}
		return true;
	}

	// ��֤���֤��
	public static boolean showIDCardToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("������" + fieldname+tableName);
			return false;
		} else if (null != field && !isIndentity(field)) {
			XCToolkit.showToast("��������ȷ�����֤��"+tableName);
			return false;
		}
		return true;
	}

	// ��֤�ʱ�
	public static boolean showPcodeToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("������" + fieldname+tableName);
			return false;
		} else if (null != field && !isPcode(field)) {
			XCToolkit.showToast("��������ȷ���ʱ�"+tableName);
			return false;
		}
		return true;
	}

	// ��֤�绰����
	public static boolean showPhoneToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("������" + fieldname+tableName);
			return false;
		} else if ((null != field&&!isPhoneNum(field))||11 != field.length()) {
			XCToolkit.showToast("��������ȷ�ĵ绰����"+tableName);
			return false;
		}
		return true;
	}
	
	// ��֤���֤
	public static boolean showIDToast(String field, String fieldname) {
		if ("".equals(field)) {
			XCToolkit.showToast("������" + fieldname+tableName);
			return false;
		} else if (null != field && !CertiCodeCheck.isIdCard(field)) {
			XCToolkit.showToast("��������ȷ�����֤����"+tableName);
			return false;
		}
		return true;
	}
}
