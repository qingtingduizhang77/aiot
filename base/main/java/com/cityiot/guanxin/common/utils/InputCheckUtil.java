package com.cityiot.guanxin.common.utils;





import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * 输入字段的校验
 * 
 * */
public class InputCheckUtil {

	/**
	 * 手机号码校验
	 * 
	 * @param telephone
	 *            手机号码
	 */
	public boolean checkPhone(String telephone) {
		String regex = "\\d{11}";
		return patternUtil(telephone, regex);

	}

	/**
	 * 密码校验
	 * 
	 * @param password
	 *            用户密码
	 * */

	public boolean checkPassword(String password) {
		
		String regex = "^(?!(?:\\d+|[a-zA-Z]+)$)[\\da-zA-Z]{6,12}$";
		return patternUtil(password, escapeExpr(regex));
	}

	/**
	 * 加上转义字符
	 * 
	 * */
	public static String escapeExpr(String strWord){
		if(StringUtils.isEmpty(strWord)){
	        String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
	        for(String key:fbsArr){
	        	if(strWord.contains(key)){
	        		strWord.replace(key, "\\"+key);
	        	}
	        }
		}
		return strWord;
	}
	
	
	/**
	 * 校验器
	 * 
	 * @param str
	 *            要检验的字段
	 * @param regex
	 *            正则
	 * @return boolean
	 * */
	public boolean patternUtil(String str, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return !matcher.matches();
	}

}
