package com.lord.learn.design.factory;

public class VolunteerFactory implements IFactory {

	@Override
	public LeiFeng createLeiFeng() {
		return new Volunteer();
	}

}
