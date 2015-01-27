package com.lord.learn.design.template;

public class ConcreteClassA extends AbstractTemplate{

	@Override
	public void primitive1() {
		System.out.println("具体A类实现方法1");
	}

	@Override
	public void primitive2() {
		System.out.println("具体A类实现方法2");
	}

}
