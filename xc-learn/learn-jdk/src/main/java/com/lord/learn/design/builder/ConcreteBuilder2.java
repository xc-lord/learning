package com.lord.learn.design.builder;

public class ConcreteBuilder2 extends Builder {
	private Product product = new Product();

	@Override
	public void bulidPartA() {
		product.add("部件X");
	}

	@Override
	public void bulidPartB() {
		product.add("部件Y");
		
	}

	@Override
	public Product getResult() {
		return product;	
	}

}
