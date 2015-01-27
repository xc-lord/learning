package com.lord.learn.design.state;

public class Work {
	private int hour;
	private boolean finish = false;
	private AbstractState state;
	
	public Work() {
		state = new ForenoonState();
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	public AbstractState getState() {
		return state;
	}
	public void setState(AbstractState state) {
		this.state = state;
	}
	
	public void doSomething() {
		state.doSomething(this);
	}
	
	
}
