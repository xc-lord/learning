package com.lord.utils;

import java.util.Properties;

public class PropertiesUtil {
	/**
	 * 
	 * @param filePath  配置文件路径（默认搜寻路径为classpath路径下）
	 * @return  返回配置属性键值对
	 */
	public static Properties readConfigFile(String filePath) {
		Properties property = new Properties();
		try {
			property.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(
					filePath));
		} catch (java.io.IOException e) {
			e.printStackTrace();
			return null;
		}
		return property;
	}
}
