package com.lord.learn.design.state;

public class ForenoonState extends AbstractState {

	@Override
	public void doSomething(Work work) {
		if(work.getHour() < 12) {
			System.out.println("当前时间：" + work.getHour() + "点，上午工作时间");
		} else {
			work.setState(new NoonState());
			work.doSomething();
		}
	}

}
