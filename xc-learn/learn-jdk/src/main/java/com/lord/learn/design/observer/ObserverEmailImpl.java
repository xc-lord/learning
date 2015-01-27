package com.lord.learn.design.observer;

/**
 * Created by xj_xiaocheng on 2015/1/19.
 */
public class ObserverEmailImpl extends AbstractObserver {

    @Override
    public void execute(Object obj) {
        System.out.println(obj + "我是系统邮件，给用户发送了邮件。");
    }
}
