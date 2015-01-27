package com.lord.learn.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.lord.model.User;

public class TestReflect {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Class clazz = User.class;
		Object obj = createInstance(clazz);
		System.out.println(obj);
		
		invokeMethod(obj, "showName");
		showfield(clazz);
	}
	
	/**
	 * 反射获得构造方法
	 */
	public static Object createInstance(Class clazz) throws Exception {
		Constructor cos = clazz.getConstructor(String.class);//根据参数获得不同方法
		Object obj = cos.newInstance("因河为池");
		return obj;
	}
	
	/**
	 * 通过反射调用方法
	 */
	public static void invokeMethod(Object obj, String methodName) throws Exception {
		Method[] ms = obj.getClass().getDeclaredMethods();//获得该类定义的所有方法,包括私有方法
		ms = obj.getClass().getMethods();//获得所有公共方法,包括从父类继承的
		for(Method m : ms) {
			//System.out.println(m.getName());
			if(methodName.equals(m.getName()))
				m.invoke(obj, null);
		}
		
		Method m = obj.getClass().getMethod(methodName, null);
		m.invoke(obj, null);
	}

	public static void showfield(Class clazz) {
		Field[] fd = clazz.getDeclaredFields();//获得私有属性
		//fd = clazz.getFields();//获得公有属性
		for (Field field : fd) {
			System.out.println(field.getName());
		}
	}
}
