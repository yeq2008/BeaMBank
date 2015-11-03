package com.bea.mbank.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 崔玉国
 *    身份证校验
 */
public class CertiCodeCheck {
	private CertiCodeCheck() {}

	public enum CertiCodeCheckResult {
	    CertiCodeCheckResult_Success,//校验成功
	    CertiCodeCheckResult_BitInvalid,//位数不对
	    CertiCodeCheckResult_BirthdayInvalid,//生日格式不对
	    CertiCodeCheckResult_ParityBitInvalid,//校验位不对
	    CertiCodeCheckResult_OtherInvalid,//其他异常
	    CertiCodeCheckResult_CharacterInvalid//字符异常
	};
	public static CertiCodeCheckResult check(String certiCode) {
		return new CertiCodeCheck().checkCertiCode(certiCode);
	}
	/**
	 * 判断身份证号码是否有效
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
	 * 检查校验位1
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
	 * 检查日期格式
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
	 * 校验身份证
	 *
	 * @param certiCode
	 *            待校验身份证
	 * @return 0--校验成功; 1--位数不对; 2--生日格式不对 ; 3--校验位不对 ; 4--其他异常;5--字符异常;
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
	 * 检查字符串是否全为数字
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
	 * 判断身份证号码区域是否有效
	 *
	 * @param idCard
	 * @return
	 */
	private boolean isAreaCard(String idCard) {
		Map<String, String> areas = new HashMap<String, String>();
		areas.put("11", "北京");areas.put("12", "天津");areas.put("13", "河北");
		areas.put("14", "山西");areas.put("15", "内蒙古");areas.put("21", "辽宁");
		areas.put("22", "吉林");areas.put("23", "黑龙江");areas.put("31", "上海");
		areas.put("32", "江苏");areas.put("33", "浙江");areas.put("34", "安徽");
		areas.put("35", "福建");areas.put("36", "江西");areas.put("37", "山东");
		areas.put("41", "河南");areas.put("42", "湖北");areas.put("43", "湖南");
		areas.put("44", "广东");areas.put("45", "广西");areas.put("46", "海南");
		areas.put("50", "重庆");areas.put("51", "四川");areas.put("52", "贵州");
		areas.put("53", "云南");areas.put("54", "**");areas.put("61", "陕西");
		areas.put("62", "甘肃");areas.put("63", "青海");areas.put("64", "宁夏");
		areas.put("65", "新疆");areas.put("71", "台湾");areas.put("81", "**");
		areas.put("82", "澳门");areas.put("91", "国外");

	    String areaKey = idCard.substring(0, 2);
	    if (areas.get(areaKey) != null) {
	        return true;
	    } else {
	        return false;
	    }
	}

}
