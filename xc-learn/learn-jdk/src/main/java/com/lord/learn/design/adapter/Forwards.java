package com.lord.learn.design.adapter;

public class Forwards extends Player {

	public Forwards(String name) {
		super(name);
	}

	@Override
	public void attack() {
		System.out.println("前锋" + this.name + ",进攻");
	}

	@Override
	public void defanse() {
		System.out.println("前锋" + this.name + ",防守");
	}

}
