package com.lord.learn.design.facade;

public class Facade {
	SubSystemOne one;
	SubSystemTwo two;
	
	public Facade() {
		one = new SubSystemOne();
		two = new SubSystemTwo();
	}
	
	public void methodA() {
		System.out.println("方法组------");
		one.methodOne();
		two.methodTwo();
	}
}
