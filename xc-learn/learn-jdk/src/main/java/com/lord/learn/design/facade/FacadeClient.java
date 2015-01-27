package com.lord.learn.design.facade;

public class FacadeClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Facade facade = new Facade();
		
		facade.methodA();
	}

}
