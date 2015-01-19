package com.lord.design.observer;

/**
 * Created by xj_xiaocheng on 2015/1/19.
 */
public class ObserverAccountantImpl extends AbstractObserver {

    @Override
    public void execute() {
        System.out.println("我是会记，给用户打印发票。");
    }
}
