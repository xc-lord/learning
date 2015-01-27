package com.lord.learn.design.proxy;

public class RealSubject extends Subject {

	@Override
	public void request() {
		System.out.println("真实想法");
	}

}
