package com.lord.learn.design.decorator;

public class ConcreteDecoratorA extends Decorator{

	@Override
	public void operation() {
		super.operation();
		System.out.println("具体装饰对象A的操作");
	}
	
}
