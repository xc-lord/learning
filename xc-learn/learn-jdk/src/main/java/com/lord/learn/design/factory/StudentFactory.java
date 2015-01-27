package com.lord.learn.design.factory;

public class StudentFactory implements IFactory{

	@Override
	public LeiFeng createLeiFeng() {
		return new Student();
	}
	
}
