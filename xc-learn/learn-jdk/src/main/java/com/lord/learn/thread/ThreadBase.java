package com.lord.learn.thread;

/**
 * 多线程
 * @author xj_xiaocheng
 * @Data   2013-7-19
 */
public class ThreadBase extends Thread {
	private String threadName;
	
	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println("当前运行的线程：" + threadName + " run in " + i);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadBase threadA = new ThreadBase();
		threadA.setThreadName("Thread A");
		ThreadBase threadB = new ThreadBase();
		threadB.setThreadName("Thread B");
		
		//threadA.run();
		//threadB.run();
		
		threadA.start();
		threadB.start();
	}

}
