package com.lord.learn.design.adapter;

public class Center extends Player {

	public Center(String name) {
		super(name);
	}

	@Override
	public void attack() {
		System.out.println("中锋" + this.name + ",进攻");
	}

	@Override
	public void defanse() {
		System.out.println("中锋" + this.name + ",防守");
	}

}