package com.lord.learn.design.factory;
/**
 * 工厂方法
 * @author xj_xiaocheng
 *
 */
public class FactoryClient {
	
	public static void main(String[] args) {
		IFactory factory = new VolunteerFactory();
		LeiFeng student = factory.createLeiFeng();
		
		student.helpPeople();
	}
}
