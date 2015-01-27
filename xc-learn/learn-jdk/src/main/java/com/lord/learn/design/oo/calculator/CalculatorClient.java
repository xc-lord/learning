package com.lord.learn.design.oo.calculator;

public class CalculatorClient {

	public static void main(String[] args) {
		Operation oper = OperationFactory.createOperation("+");
		oper.setNumber1(20.2);
		oper.setNumber2(4.5);
		System.out.println(oper.getResult());
	}

}
