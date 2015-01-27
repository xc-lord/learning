package com.lord.learn.design.oo.calculator;

public class OperationSub extends Operation {

	@Override
	public double getResult() {
		double result = 0;
		result = getNumber1() - getNumber2();
		return result;
	}

}
