package com.lord.learn.design.builder;

public class ConcreteBuilder1 extends Builder {
	private Product product = new Product();

	@Override
	public void bulidPartA() {
		product.add("部件A");
	}

	@Override
	public void bulidPartB() {
		product.add("部件B");
		
	}

	@Override
	public Product getResult() {
		return product;	
	}

}
