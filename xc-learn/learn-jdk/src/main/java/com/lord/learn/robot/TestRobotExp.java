package com.lord.learn.robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class TestRobotExp {
	
	public static void main(String[] args) {
		try {
			Robot robot = new Robot();
			// 定义5秒的延迟以便你打开notepad
			// Robot 开始写
			robot.delay(5000);
			robot.keyPress(KeyEvent.VK_H);
			robot.keyPress(KeyEvent.VK_I);
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyPress(KeyEvent.VK_C);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyPress(KeyEvent.VK_O);
			robot.keyPress(KeyEvent.VK_E);
			robot.keyPress(KeyEvent.VK_R);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}