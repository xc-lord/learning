package com.lord.learn.design.oo.calculator;

public class OperationFactory {
	public static Operation createOperation(String operate) {
		Operation operation = null;
		if(operate == null || operate.equals(""))
			return null;
		if(operate.equals("+")) {
			operation = new OperationAdd();
		} else if(operate.equals("-")) {
			operation = new OperationSub();
		}
		
		return operation;
	}
}
