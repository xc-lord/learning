package com.lord.learn.design.command;

public class FlyToBeijing implements Command {

	@Override
	public void execute() {
		System.out.println("飞到上海");
	}

	@Override
	public void undo() {
		System.out.println("从上海飞回来");
	}

}
