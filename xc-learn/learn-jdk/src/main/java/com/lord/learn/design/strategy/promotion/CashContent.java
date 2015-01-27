package com.lord.learn.design.strategy.promotion;

public class CashContent {
	private CashSuper cashSuper;

	public CashContent(CashSuper cashSuper) {
		this.cashSuper = cashSuper;
	}
	
	public double getResult(double money) {
		return cashSuper.acceptCash(money);
	}

}
