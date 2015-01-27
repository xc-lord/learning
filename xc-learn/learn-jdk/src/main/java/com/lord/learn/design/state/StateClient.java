package com.lord.learn.design.state;
/**
 * 状态模式
 * @author xj_xiaocheng
 *
 */
public class StateClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Work w = new Work();
		w.setHour(9);
		w.doSomething();
		
		w.setHour(12);
		w.doSomething();
		
		w.setHour(20);
		w.doSomething();
		
	}

}
