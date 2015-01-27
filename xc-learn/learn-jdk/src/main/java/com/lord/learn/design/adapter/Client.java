package com.lord.learn.design.adapter;
/**
 * 适配器模式
 * @author xj_xiaocheng
 *
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Player b = new Forwards("巴神");
		b.attack();
		
		Player k = new Forwards("科比");
		k.attack();
		
		Player ym = new Translator("姚明");
		ym.attack();
		ym.defanse();
	}

}
