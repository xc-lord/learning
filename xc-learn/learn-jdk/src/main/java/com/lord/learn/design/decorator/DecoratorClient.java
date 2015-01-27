package com.lord.learn.design.decorator;
/**
 * 装饰者模式
 * @author xj_xiaocheng
 *
 */
public class DecoratorClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConcreteComponent c = new ConcreteComponent();
		ConcreteDecoratorA a = new ConcreteDecoratorA();
		ConcreteDecoratorB b = new ConcreteDecoratorB();
		
		a.setComponent(c);
		b.setComponent(a);
		b.operation();
	}

}
