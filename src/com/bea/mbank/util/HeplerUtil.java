package com.bea.mbank.util;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bea.database.codemodel.YRL_BASIC_DATA;
import com.bea.grouptagpop.CharacterParser;

/**
 * 
 * @author xra 辅助工具类
 * 
 */
public class HeplerUtil {

	private static HeplerUtil helperutil = null;

	public static HeplerUtil getInstance() {
		if (helperutil == null) {
			helperutil = new HeplerUtil();
		}
		return helperutil;
	}
	
	/**
	 * 码表排序
	 * @param list
	 * @return
	 */
	public static List<YRL_BASIC_DATA> sortYRL_BASIC_DATA(List<YRL_BASIC_DATA> list){
		try {
			CharacterParser parser = CharacterParser.getInstance();
			for(YRL_BASIC_DATA ybd : list){
				String pinyin = parser.getSelling(ybd.getNAME());
				String sortString = pinyin.substring(0, 1).toUpperCase();
				ybd.setORDER_NO(sortString);
			}
			
			Collections.sort(list, new Comparator<YRL_BASIC_DATA>() {
				@Override
				public int compare(YRL_BASIC_DATA lhs, YRL_BASIC_DATA rhs) {
					if(null != lhs.getORDER_NO() && null != rhs.getORDER_NO())
						return lhs.getORDER_NO().compareTo(rhs.getORDER_NO());
					return 1;
				}
			});
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 验证手机号是否合法
	 * 
	 */

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	/**
	 * 验证手机号
	 */ 
	public static boolean isPhoneNum(String mobiles) {
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	/**
	 * 验证邮箱是否合法
	 * 
	 */

	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 验证身份证号是否合法
	 * 
	 */

	public static boolean isIndentity(String Indentity) {
		String str = "\\d{15}|\\d{17}[0-9xX]";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(Indentity);
		return m.matches();

	}

	/**
	 * 验证邮编是否合法
	 * 
	 */

	public static boolean isPcode(String Pcode) {
		String str = "[1-9]\\d{5}(?!\\d)";

		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(Pcode);
		return m.matches();

	}

	/**
	 * 匹配中文字符
	 * 
	 */
	public static boolean isChinese(String infor) {
		String str = "[\u4e00-\u9fff]+$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(infor);

		return m.matches();
	}

	/**
	 * 功能：判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 功能：判断字符串是否为特定数量的数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str,int x) {
		Pattern pattern = Pattern.compile("[0-9]"+"{"+x+"}");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能：判断字符串是否为小数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumericDecimal(String str) {
		Pattern pattern = Pattern.compile("[\\d.]+");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 功能：判断字符串是否是英文
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEngStr(String str) {
		Pattern pattern = Pattern.compile("[A-Za-z ]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 功能：判断字符串是否是利率
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isRate(String str){
		Pattern pattern=Pattern.compile("((^[+-][1-9]\\d*)|(^[1-9]\\d*))((\\.\\d+)|(\\d{0,}))");
		Matcher isRate = pattern.matcher(str);
		if (isRate.matches()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 日期相减
	 * @param date1当前日期
	 * @param date2入职日期
	 * @return
	 */
	public int getMonthNum(Date date1,Date date2) {
		Calendar cal1=Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2=Calendar.getInstance();
		cal2.setTime(date2);
		return ((cal1.get(Calendar.YEAR)-cal2.get(Calendar.YEAR))*12)+(cal1.get(Calendar.MONTH)-cal2.get(Calendar.MONTH));
	}
}
