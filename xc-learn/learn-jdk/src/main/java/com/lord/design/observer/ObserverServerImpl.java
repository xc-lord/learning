package com.lord.design.observer;

/**
 * Created by xj_xiaocheng on 2015/1/19.
 */
public class ObserverServerImpl extends AbstractObserver {
    @Override
    public void execute() {
        System.out.println("我是客服，给用户发货。");
    }
}