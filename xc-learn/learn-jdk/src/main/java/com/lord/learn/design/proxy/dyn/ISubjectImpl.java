package com.lord.learn.design.proxy.dyn;

public class ISubjectImpl implements ISubject {

	@Override
	public void request() {
		System.out.println("ISubjectImpl request do something");
	}

	@Override
	public void doSomething() {
		System.out.println("ISubjectImpl:" + getMessage());
	}

	@Override
	public String getMessage() {
		return "原返回值:四个";
	}

}
