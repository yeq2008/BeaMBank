package com.bea.mbank.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author �����
 *    ���֤У��
 */
public class CertiCodeCheck {
	private CertiCodeCheck() {}

	public enum CertiCodeCheckResult {
	    CertiCodeCheckResult_Success,//У��ɹ�
	    CertiCodeCheckResult_BitInvalid,//λ������
	    CertiCodeCheckResult_BirthdayInvalid,//���ո�ʽ����
	    CertiCodeCheckResult_ParityBitInvalid,//У��λ����
	    CertiCodeCheckResult_OtherInvalid,//�����쳣
	    CertiCodeCheckResult_CharacterInvalid//�ַ��쳣
	};
	public static CertiCodeCheckResult check(String certiCode) {
		return new CertiCodeCheck().checkCertiCode(certiCode);
	}
	/**
	 * �ж����֤�����Ƿ���Ч
	 *
	 * @param idCard
	 * @return
	 */
	public static boolean isIdCard(String idCard) {
	    if (check(idCard) == CertiCodeCheckResult.CertiCodeCheckResult_Success)
	        return true;
	    else
	        return false;
	}
	
	
	/**
	 * ���У��λ1
	 *
	 * @param certiCode
	 * @return
	 */
	private boolean checkIDParityBit(String certiCode) {
	    boolean flag = false;
	    if (certiCode.length() <= 0)
	        return false;
	    int ai[] = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
	    byte cr[] = certiCode.getBytes();
	    if (certiCode.length() == 18) {
	        int i = 0;
	        for (int k = 0; k < 18; k++) {
	            byte c = cr[k];
	            int j;
	            if (c == 'X'||c=='x')
	                j = 10;
	            else if (c <= '9' || c >= '0')
	                j = c - 48;
	            else
	                return flag;
	            i += j * ai[k];
	        }
	        if (i % 11 == 1)
	            flag = true;
	    }
	    return flag;
	}

	/**
	 * ������ڸ�ʽ
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private boolean checkDate(String birthday) {
		Date date = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			date = dateFormat.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null != date;
	}

	/**
	 * У�����֤
	 *
	 * @param certiCode
	 *            ��У�����֤
	 * @return 0--У��ɹ�; 1--λ������; 2--���ո�ʽ���� ; 3--У��λ���� ; 4--�����쳣;5--�ַ��쳣;
	 * @param certiCode
	 * @return
	 */
	private CertiCodeCheckResult checkCertiCode(String certiCode) {
	    try {
	        if (certiCode.length() != 15 && certiCode.length() != 18) {
	            return CertiCodeCheckResult.CertiCodeCheckResult_BitInvalid;
	        }
	        if (certiCode.length() == 15) {
	            if (!isAreaCard(certiCode)) {
	                return CertiCodeCheckResult.CertiCodeCheckResult_ParityBitInvalid;
	            }
	            if (!checkFigure(certiCode)) {
	                return CertiCodeCheckResult.CertiCodeCheckResult_CharacterInvalid;
	            }
	            if (!checkDate("19"+ certiCode.substring(6, 12)))
	                return CertiCodeCheckResult.CertiCodeCheckResult_BirthdayInvalid;
	        }
	        if (certiCode.length() == 18) {
	            if (!isAreaCard(certiCode)) {
	                return CertiCodeCheckResult.CertiCodeCheckResult_ParityBitInvalid;
	            }
	            if (!checkFigure(certiCode.substring(0, 17))) {
	                return CertiCodeCheckResult.CertiCodeCheckResult_CharacterInvalid;
	            }
	            if (!checkDate(certiCode.substring(6, 14)))
	                return CertiCodeCheckResult.CertiCodeCheckResult_BirthdayInvalid;
	            if (!checkIDParityBit(certiCode))
	                return CertiCodeCheckResult.CertiCodeCheckResult_ParityBitInvalid;
	        }
	    }
	    catch (Exception e) {
	        return CertiCodeCheckResult.CertiCodeCheckResult_OtherInvalid;
	    }
	    return CertiCodeCheckResult.CertiCodeCheckResult_Success;
	}

	

	/**
	 * ����ַ����Ƿ�ȫΪ����
	 *
	 * @param certiCode
	 * @return
	 */
	private boolean checkFigure(String string) {
	    if (string.length() <= 0) {
	        return false;
	    }
	    byte chars[] = string.getBytes();
	    for (int i = 0; i < string.length(); ++i) {
	        byte ch = chars[i];
	        if (!(ch >= '0' && ch <= '9')) {
	            return false;
	        }
	    }
	    
	    return true;
	}

	/**
	 * �ж����֤���������Ƿ���Ч
	 *
	 * @param idCard
	 * @return
	 */
	private boolean isAreaCard(String idCard) {
		Map<String, String> areas = new HashMap<String, String>();
		areas.put("11", "����");areas.put("12", "���");areas.put("13", "�ӱ�");
		areas.put("14", "ɽ��");areas.put("15", "���ɹ�");areas.put("21", "����");
		areas.put("22", "����");areas.put("23", "������");areas.put("31", "�Ϻ�");
		areas.put("32", "����");areas.put("33", "�㽭");areas.put("34", "����");
		areas.put("35", "����");areas.put("36", "����");areas.put("37", "ɽ��");
		areas.put("41", "����");areas.put("42", "����");areas.put("43", "����");
		areas.put("44", "�㶫");areas.put("45", "����");areas.put("46", "����");
		areas.put("50", "����");areas.put("51", "�Ĵ�");areas.put("52", "����");
		areas.put("53", "����");areas.put("54", "**");areas.put("61", "����");
		areas.put("62", "����");areas.put("63", "�ຣ");areas.put("64", "����");
		areas.put("65", "�½�");areas.put("71", "̨��");areas.put("81", "**");
		areas.put("82", "����");areas.put("91", "����");

	    String areaKey = idCard.substring(0, 2);
	    if (areas.get(areaKey) != null) {
	        return true;
	    } else {
	        return false;
	    }
	}

}
