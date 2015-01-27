package com.lord.learn.design.command;

public interface Command {

	/**
	 * 执行命令
	 */
	public void execute();
	
	/**
	 * 撤销命令
	 */
	public void undo();
}
