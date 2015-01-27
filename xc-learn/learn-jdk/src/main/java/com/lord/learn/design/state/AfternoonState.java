package com.lord.learn.design.state;

public class AfternoonState extends AbstractState {

	@Override
	public void doSomething(Work work) {
		System.out.println("当前时间：" + work.getHour() + "点，下班");
	}

}
