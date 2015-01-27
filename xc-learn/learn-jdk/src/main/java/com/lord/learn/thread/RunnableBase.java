package com.lord.learn.thread;

import java.util.ArrayList;
import java.util.List;

import com.lord.learn.thread.pool.TestThreadPool;

/**
 * 多线程
 * @author xj_xiaocheng
 * @Data   2013-7-19
 */
public class RunnableBase implements Runnable {
	private String threadName;
	private List<String> list;
	private int size;
	
	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public void run() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(size > 0 && TestThreadPool.count > 0) {
			size--;
			//System.out.println(threadName + "正在买票：" + TestThreadPool.count--);
		}
	}
	
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RunnableBase myRun = new RunnableBase();
		List<String> list = new ArrayList<String>();
		list.add("list 1");
		list.add("list 2");
		list.add("list 3");
		list.add("list 4");
		list.add("list 5");
		myRun.setList(list);
		
		RunnableBase myRun2 = new RunnableBase();
		List<String> list2 = new ArrayList<String>();
		list2.add("list2 1");
		list2.add("list2 2");
		list2.add("list2 3");
		list2.add("list2 4");
		list2.add("list2 5");
		myRun2.setList(list2);
		
		Thread thread1 = new Thread(myRun, "1号窗口");
		Thread thread2 = new Thread(myRun2, "2号窗口");
		
		thread1.start();
		thread2.start();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
