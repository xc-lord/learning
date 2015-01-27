package com.lord.learn.design.strategy.promotion;
/**
 * 满减
 * @author xj_xiaocheng
 *
 */
public class CashReturn extends CashSuper {
	private double moneyCondition;
	private double moneyReturn;
	
	CashReturn(String condition, String mreturn) {
		this.moneyCondition = Double.parseDouble(condition);
		this.moneyReturn = Double.parseDouble(mreturn);
	}

	@Override
	public double acceptCash(double money) {
		double result = 0;
		if(money > moneyCondition) {
			result = money - moneyReturn;
		}
		return result;
	}

}
