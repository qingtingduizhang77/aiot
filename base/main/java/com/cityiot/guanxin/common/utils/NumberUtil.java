package com.cityiot.guanxin.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 数字处理工具类
 * 
 */
public class NumberUtil {
	
	private static final Logger log = LoggerFactory.getLogger(NumberUtil.class);

	public static final DecimalFormat PERCENT = new DecimalFormat("0.00%");


	public static Double getNumOrDefault(Double num) {
		return num == null ? 0 : num;
	}

	/**
	 * 判断是否是纯数字（两头空格符忽略）
	 * @param obj
	 * @return
	 */
	public static final boolean isNumeric(Object obj) {
		return !StringUtils.isEmpty(obj) && StringUtils.trimWhitespace(obj.toString()).matches("^-?\\d+$");
	}
	
	/**
	 * 转换为整型(Integer)
	 * 
	 * @param obj
	 *            待转换对象
	 * @param defaultVal
	 *            默认值(无法转换时返回该值)
	 * @return Integer
	 */
	public static final Integer toInteger(Object obj, int defaultVal) {
		try {
			return Integer.parseInt(obj.toString());
		} catch (Exception e) {
			log.warn("parse integer failed : {}, defaultVal : {}", obj, defaultVal);
			return defaultVal;
		}
	}

	/**
	 * 转换为整型(Integer)
	 * 
	 * @param obj
	 *            待转换对象
	 * @return Integer
	 */
	public static final Integer toInteger(Object obj) {
		try {
			return Integer.parseInt(obj.toString());
		} catch (Exception e) {
			log.warn("parse integer failed : {}", obj);
			return null;
		}
	}

	/**
	 * 转换为长整型(Long)
	 * 
	 * @param obj
	 *            待转换对象
	 * @param defaultVal
	 *            默认值(无法转换时返回该值)
	 * @return Long
	 */
	public static final Long toLong(Object obj, long defaultVal) {
		try {
			return Long.parseLong(obj.toString());
		} catch (Exception e) {
			log.warn("parse long failed : {}, defaultVal : {}", obj, defaultVal);
			return defaultVal;
		}
	}

	/**
	 * 转换为长整型(Long)
	 * 
	 * @param obj
	 *            待转换对象
	 * @return Long
	 */
	public static final Long toLong(Object obj) {
		try {
			return Long.parseLong(obj.toString());
		} catch (Exception e) {
			log.warn("parse long failed : {}", obj);
			return null;
		}
	}

	/**
	 * 转换为浮点数(Float)
	 * 
	 * @param obj
	 *            待转换对象
	 * @param defaultVal
	 *            默认值(无法转换时返回该值)
	 * @return Float
	 */
	public static final Float toFloat(Object obj, float defaultVal) {
		try {
			return Float.parseFloat(obj.toString());
		} catch (Exception e) {
			log.warn("parse float failed : {}, defaultVal : {}", obj, defaultVal);
			return defaultVal;
		}
	}

	/**
	 * 转换为浮点数(Float)
	 * 
	 * @param obj
	 *            待转换对象
	 * @param defaultVal
	 *            默认值(无法转换时返回该值)
	 * @return Float
	 */
	public static final Float toFloat(Object obj) {
		try {
			return Float.parseFloat(obj.toString());
		} catch (Exception e) {
			log.warn("parse float failed : {}", obj);
			return null;
		}
	}

	/**
	 * 转换为双精度浮点数(Double)
	 * 
	 * @param obj
	 *            待转换对象
	 * @param defaultVal
	 *            默认值(无法转换时返回该值)
	 * @return Double
	 */
	public static final Double toDouble(Object obj, double defaultVal) {
		try {
			return Double.parseDouble(obj.toString());
		} catch (Exception e) {
			log.warn("parse double failed : {}, defaultVal : {}", obj, defaultVal);
			return defaultVal;
		}
	}

	/**
	 * 转换为双精度浮点数(Double)
	 * 
	 * @param obj
	 *            待转换对象
	 * @return Double
	 */
	public static final Double toDouble(Object obj) {
		try {
			return Double.parseDouble(obj.toString());
		} catch (Exception e) {
			log.warn("parse double failed : {}", obj);
			return null;
		}
	}
	
	/**
	 * 将字符串转换为Integer集合
	 * 
	 * @param obj
	 *            待转换字符串
	 * @param separtor
	 *            分隔符
	 * @return List<Integer>
	 */
	public static final List<Integer> toIntList(Object obj, String separtor) {
		List<Integer> list = new ArrayList<Integer>();
		if (!StringUtils.isEmpty(obj)) {
			String[] temp = StringUtils.trimWhitespace(obj.toString()).split(separtor);
			for (String str : temp) {
				Integer i = toInteger(str);
				if (i != null) {
					list.add(i);
				}
			}
		}
		return list;
	}

	/**
	 * 将字符串转换为Integer集合(分隔符:",")
	 * 
	 * @param obj
	 *            待转换字符串
	 * @return List<Integer>
	 */
	public static final List<Integer> toIntList(Object obj) {
		return toIntList(obj, ",");
	}

	/**
	 * 将字符串转换为Integer数组
	 * 
	 * @param obj
	 *            待转换字符串
	 * @param separtor
	 *            分隔符
	 * @return Integer[]
	 */
	public static final Integer[] toIntArray(Object obj, String separtor) {
		return toIntList(obj, separtor).toArray(new Integer[0]);
	}

	/**
	 * 将字符串转换为Integer数组(分隔符:",")
	 * 
	 * @param obj
	 *            待转换字符串
	 * @return Integer[]
	 */
	public static final Integer[] toIntArray(Object obj) {
		return toIntList(obj, ",").toArray(new Integer[0]);
	}

	/**
	 * 将字符串转换为Long集合
	 * 
	 * @param obj
	 *            待转换字符串
	 * @param separtor
	 *            分隔符
	 * @return List<Long>
	 */
	public static final List<Long> toLongList(Object obj, String separtor) {
		List<Long> list = new ArrayList<Long>();
		if (!StringUtils.isEmpty(obj)) {
			String[] temp = StringUtils.trimWhitespace(obj.toString()).split(separtor);
			for (String str : temp) {
				Long l = toLong(str);
				if (l != null) {
					list.add(l);
				}
			}
		}
		return list;
	}

	/**
	 * 将字符串转换为Long集合(分隔符:",")
	 * 
	 * @param obj
	 *            待转换字符串
	 * @return List<Long>
	 */
	public static final List<Long> toLongList(Object obj) {
		return toLongList(obj, ",");
	}

	/**
	 * 将字符串转换为Long数组
	 * 
	 * @param obj
	 *            待转换字符串
	 * @param separtor
	 *            分隔符
	 * @return Long[]
	 */
	public static final Long[] toLongArray(Object obj, String separtor) {
		return toLongList(obj, separtor).toArray(new Long[0]);
	}
	
	/**
	 * 将字符串转换为Long数组(分隔符:",")
	 * 
	 * @param obj
	 *            待转换字符串
	 * @return Long[]
	 */
	public static final Long[] toLongArray(Object obj) {
		return toLongList(obj, ",").toArray(new Long[0]);
	}
	
	/**
	 * 手机号码正则表达式校验
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile){
		
		if(StringUtils.isEmpty(mobile)){
			return false;
		}
		Pattern p = Pattern.compile("^(1[3,4,5,7,8][0-9])\\d{8}$");
		Matcher m = p.matcher(mobile);
		return m.matches();

	}


}
