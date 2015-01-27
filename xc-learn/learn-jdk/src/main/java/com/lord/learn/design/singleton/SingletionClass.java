package com.lord.learn.design.singleton;
/**
 * 使用静态内部类实现单例
 * @author xj_xiaocheng
 *
 */
public class SingletionClass {
	/**
	 * 只有被调用时才会加载
	 * @author xj_xiaocheng
	 *
	 */
	private static class SingletionHolder {
		private static SingletionClass instance = new SingletionClass();
		
	}
	/**
	 * 私有化构造方法
	 */
	private SingletionClass() {
		
	}
	/**
	 * 获得唯一实例
	 * @return
	 */
	public static SingletionClass getInstance() {
		return SingletionHolder.instance;
	}
}
