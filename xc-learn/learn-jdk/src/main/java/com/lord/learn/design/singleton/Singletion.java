package com.lord.learn.design.singleton;
/**
 * 使用枚举，实现单例
 * @author xj_xiaocheng
 *
 */
public enum Singletion {
	instance;

	public void operation() {
		System.out.println("单例调用的方法");
	}

	public static Singletion getInstance() {
		return instance;
	}
}
