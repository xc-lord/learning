package com.lord.learn.design.reactor;

/**
 * Created by xj_xiaocheng on 2015/1/22.
 */
public class NioServer {

    public static void main(String[] args) throws Exception {
        Reactor reactor = new Reactor(5700);
        Thread thread = new Thread(reactor);
        thread.start();
    }
}
