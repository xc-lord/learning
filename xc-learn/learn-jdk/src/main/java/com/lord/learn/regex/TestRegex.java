package com.lord.learn.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		getChinese("welcome to china,江西奉新,welcome,你!");
		
		String str = "http://www.gome.com.cn/ec/homeus/index.jsp";
		String reg = ".*index.jsp";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher.matches());

	}
	
	 /**
	  * 中文提取 
	  * @param str
	  */
    public static void getChinese(String str){  
        String regex = "([\u4e00-\u9fa5]+)";//u4e00-\u9fa5为汉字   
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);  
        StringBuffer sb = new StringBuffer();  
        while(matcher.find()){  
            sb.append(matcher.group());  
        }  
        System.out.println(sb);  
    }  

}
