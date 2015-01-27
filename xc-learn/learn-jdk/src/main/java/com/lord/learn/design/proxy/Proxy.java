package com.lord.learn.design.proxy;

public class Proxy extends Subject {
	private RealSubject realSubject;

	@Override
	public void request() {
		System.out.println("方法执行前");
		if(realSubject == null) {
			realSubject = new RealSubject();
		}
		realSubject.request();
	}

}
