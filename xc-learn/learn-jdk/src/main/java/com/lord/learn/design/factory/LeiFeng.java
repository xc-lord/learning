package com.lord.learn.design.factory;

public abstract class LeiFeng {
	public abstract String getName();
	
	public void helpPeople() {
		System.out.println(getName() + "助人为乐!");
	}
}
