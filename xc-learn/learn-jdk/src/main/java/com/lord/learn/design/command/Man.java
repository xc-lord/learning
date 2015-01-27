package com.lord.learn.design.command;

import java.util.ArrayList;
import java.util.List;

public class Man {
	private List<Command> commands = new ArrayList<Command>();
	private List<Command> undoCommands = new ArrayList<Command>();

	public void addCommands(Command comand) {
		this.commands.add(comand);
	}
	
	public void excuteCommands() {
		for (Command command : commands) {
			command.execute();
			undoCommands.add(command);
		}
	}
	
	public void undoCommands() {
		for (int i = undoCommands.size() - 1; i >= 0; i--) {
			Command command = undoCommands.get(i);
			command.undo();
		}
	}

}
