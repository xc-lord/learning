package com.lord.learn.design.adapter;

public class Translator extends Player {
	
	public Translator(String name) {
		super(name);
		foreignCenter.setName(name);
	}

	private ForeignCenter foreignCenter = new ForeignCenter();

	@Override
	public void attack() {
		foreignCenter.进攻();
	}

	@Override
	public void defanse() {
		foreignCenter.防守();
	}

}
