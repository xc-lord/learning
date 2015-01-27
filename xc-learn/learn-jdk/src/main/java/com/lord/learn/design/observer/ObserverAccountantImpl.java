package com.lord.learn.design.observer;

/**
 * Created by xj_xiaocheng on 2015/1/19.
 */
public class ObserverAccountantImpl extends AbstractObserver {

    @Override
    public void execute(Object obj) {
        System.out.println(obj + "我是会记，给用户打印发票。");
    }
}
