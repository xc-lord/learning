package com.lord.learn.design.observer;

/**
 * 抽象的观察者
 * Created by xj_xiaocheng on 2015/1/19.
 */
public abstract class AbstractObserver {

    /**
     * 执行方法
     */
    public void execute() {
        execute(null);
    }

    /**
     * 执行方法
     */
    public abstract void execute(Object obj);
}
