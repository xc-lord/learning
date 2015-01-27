package com.lord.learn.design.strategy.promotion;

public class CashNormal extends CashSuper {

	@Override
	public double acceptCash(double money) {
		return money;
	}

}
