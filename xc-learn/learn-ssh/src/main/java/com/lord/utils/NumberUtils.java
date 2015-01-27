package com.lord.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class NumberUtils {
	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String numberChar = "0123456789";

    /**
     * 生成随机字符串
     * @param length	随机字符串的长度
     * @return
     */
	public static String generateString(int length)
	{
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}
	
	/**
	 * 随机生成一定范围的随机数
	 * @param start	开始
	 * @param end	结束
	 * @return
	 */
	public static Integer randomNum(int start, int end) {
		int num = (int)(start+Math.random()*(end-start+1));
		return num;
	}
	
	/**
	* 日期转换成字符串
	* @param date
	* @return
	*/
	public static String dateToStr(java.util.Date date) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String str = format.format(date);
	   return str;
	}
	
	/**
	* long类型的时间转换成字符串
	* @param longTime
	* @return
	*/
	public static String longTimeToStr(long longTime) {
		return dateToStr(new Date(longTime));
	}
	
	/**
	 * long类型的时间转换成字符串
	 * @param longTime
	 * @return
	 */
	public static String longStringToDataStr(String longString) {
		return dateToStr(new Date(Long.parseLong(longString)));
	}

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date strToDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	
	/**
	 * 保留2位小数,并且不进行四舍五入
	 * @param number
	 * @return
	 */
	public String keepTwoDecimal(double number) {
		BigDecimal decimal = new BigDecimal(number);
		double f1 = decimal.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		DecimalFormat format = new DecimalFormat("#.00");
		return format.format(f1);
	}

	public static void main(String[] args) {
	  
	   Date date = new Date();
	   System.out.println("日期转字符串：" + dateToStr(date));
	   System.out.println("字符串转日期：" + strToDate(dateToStr(date)));
	  
	}
}
