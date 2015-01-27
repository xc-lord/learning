package com.lord.learn.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * java定时任务
 * @author xj_xiaocheng
 * @Data   2013-9-9
 */
public class TestScheduler {
	ScheduledExecutorService pool = null;
	
	public void initScheduler() {
		pool = Executors.newScheduledThreadPool(1);
		// 启动后2s开始第一次检查，之后每5s检查一次
		pool.scheduleWithFixedDelay(new Runnable() {

			private boolean check() {
				System.out.println("checking connection");
				return true;
			}

			@Override
			public void run() {
				if (check()) {
					System.out.println("正常运行");
					return;
				}

				while (true) {
					
				}
			}
		}, 2l, 5l, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) {
		TestScheduler test = new TestScheduler();
		test.initScheduler();
	}
}
