package com.lord.learn.thread.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.lord.learn.thread.RunnableBase;

/**
 * 功能:java线程池
 */
public class TestThreadPool {
	public static Integer count = 100000;
	public static ThreadPoolExecutor threadPoolExecutor;

	public static void main(String[] args) throws Exception {
		/*
		 corePoolSize 指的是保留的线程池大小。
		 maximumPoolSize 指的是线程池的最大大小。
		 keepAliveTime 指的是空闲线程结束的超时时间。
		 unit 是一个枚举，表示 keepAliveTime 的单位。
		 workQueue 表示存放任务的队列。 LinkedBlockingQueue的好处在于没有大小限制
		 */
		threadPoolExecutor = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		
		for (int i = 0; i < 20; i++) {
			RunnableBase myRun = new RunnableBase();
			myRun.setThreadName(i + "号窗口");
			myRun.setSize(i*200);
			Thread thread1 = new Thread(myRun);
			threadPoolExecutor.execute(thread1);
			Thread.sleep(1000L);
			long total = TestThreadPool.threadPoolExecutor.getTaskCount();
			long complated = TestThreadPool.threadPoolExecutor.getCompletedTaskCount();
			System.err.println((total - complated) + "个线程" + total + " - " + complated);
		}
	}
}
