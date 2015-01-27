package com.lord.learn.design.command;
/**
 * 命令模式
 */
public class CommandClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Man man = new Man();
		man.addCommands(new FlyToShanghai());
		man.addCommands(new FlyToBeijing());
		man.excuteCommands();
		man.undoCommands();
	}

}
