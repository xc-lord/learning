package com.lord.learn.design.command;

public class FlyToShanghai implements Command {

	@Override
	public void execute() {
		System.out.println("飞到北京");
	}

	@Override
	public void undo() {
		System.out.println("从北京飞回来");
	}

}
