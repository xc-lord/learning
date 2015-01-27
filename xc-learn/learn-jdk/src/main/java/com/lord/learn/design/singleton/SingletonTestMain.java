package com.lord.learn.design.singleton;

import java.lang.reflect.Constructor;

public class SingletonTestMain {

	/**
	 * @param args
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		Singletion.getInstance().operation();
		try {
			// 测试Singletom1
			// 拿到第一个实例
			Singletion s1 = Singletion.getInstance();
			// 测试拿到第二个实例
			Class c1 = Class.forName("com.lord.base.design.singleton.Singletion");
			Constructor[] cons = c1.getDeclaredConstructors();
			Constructor cc1 = cons[0];
			cc1.setAccessible(true);
			Singletion s2 = (Singletion) cc1.newInstance();
			System.out.println(s1 + "/" + s2);
			System.out.println(s1 == s2);
			Singletion.getInstance().operation();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
