package com.lord.learn.design.template;

public abstract class AbstractTemplate {
	public abstract void primitive1();
	public abstract void primitive2();
	
	public void templateMethod() {
		primitive1();
		primitive2();
		System.out.println("模板方法");
	}
}
