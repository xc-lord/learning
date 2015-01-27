package com.lord.learn.design.strategy.promotion;
/**
 * 策略模式
 * @author xj_xiaocheng
 *
 */
public class CashClient {
	
	public static void main(String[] args) {
		double money = 100.0;
		CashContent cc = new CashContent(new CashReturn("80", "10"));
		System.out.println("订单金额：" + cc.getResult(money));
	}
	
}
