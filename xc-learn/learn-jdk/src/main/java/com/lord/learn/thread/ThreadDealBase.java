package com.lord.learn.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多线程批处理
 * @author xj_xiaocheng
 * @Data   2013-7-19
 */
public class ThreadDealBase {
	
	private Map<String, String> cecheMap = new ConcurrentHashMap<String, String>();
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ThreadDealBase thread = new ThreadDealBase();
		thread.begin();
		thread.displayMap();
	}
	
	public void displayMap() {
		for (String key : cecheMap.keySet()) {
			System.out.println(key + " - " + cecheMap.get(key));
		}
	}
	
	public void begin() {
		DealThread myRun = new DealThread();
		
		Thread thread1 = new Thread(myRun, "1号窗口");
		Thread thread2 = new Thread(myRun, "2号窗口");
		Thread thread3 = new Thread(myRun, "3号窗口");
		Thread thread4 = new Thread(myRun, "4号窗口");
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}
	
	class DealThread implements Runnable {
		private String threadName;
		private Integer count = 100;
		
		public String getThreadName() {
			return threadName;
		}

		public void setThreadName(String threadName) {
			this.threadName = threadName;
		}

		public void run() {
			for (int i = 0; i < 20; i++) {
				if(this.count > 0) {
					cecheMap.put(Thread.currentThread().getName(), Thread.currentThread().getName() + "正在买票：" + this.count--);
					System.out.println(Thread.currentThread().getName() + "正在买票：" + this.count);
				}
			}
		}
		
	}

}
