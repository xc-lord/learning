package com.lord.learn.design.state;

public class NoonState extends AbstractState{

	@Override
	public void doSomething(Work work) {
		if(work.getHour() < 13) {
			System.out.println("当前时间：" + work.getHour() + "点，中午休息");
		} else {
			work.setState(new AfternoonState());
			work.doSomething();
		}
	}

}
