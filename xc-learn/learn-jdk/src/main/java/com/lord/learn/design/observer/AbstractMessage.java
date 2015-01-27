package com.lord.learn.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象的主题
 * Created by xj_xiaocheng on 2015/1/19.
 */
public abstract class AbstractMessage {

    private List<AbstractObserver> observerList = new ArrayList<AbstractObserver>();

    public void add(AbstractObserver o) {
        observerList.add(o);
    }

    public void remove(AbstractObserver o) {
        observerList.remove(o);
    }

    /**
     * 通知观察者
     */
    public void send() {
        for (AbstractObserver abstractObserver : observerList) {
            abstractObserver.execute();
        }
    }

    /**
     * 通知观察者
     */
    public void send(Object obj) {
        for (AbstractObserver abstractObserver : observerList) {
            abstractObserver.execute(obj);
        }
    }
}
