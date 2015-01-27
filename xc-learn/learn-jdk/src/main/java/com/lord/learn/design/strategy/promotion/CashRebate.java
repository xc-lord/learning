package com.lord.learn.design.strategy.promotion;

public class CashRebate extends CashSuper {
	private double discount = 1d;
	
	CashRebate(double meneyRebate) {
		this.discount = meneyRebate;
	}

	@Override
	public double acceptCash(double money) {
		return money*discount;
	}

}
