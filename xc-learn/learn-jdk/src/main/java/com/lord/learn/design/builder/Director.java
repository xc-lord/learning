package com.lord.learn.design.builder;

public class Director {
	public void construct(Builder builder) {
		builder.bulidPartA();
		builder.bulidPartB();
	}
}
