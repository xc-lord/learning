package com.lord.learn.design.reactor;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * Created by xj_xiaocheng on 2015/1/22.
 */
public class Acceptor implements Runnable {
    private Reactor reactor;

    public Acceptor(Reactor reactor) {
        this.reactor = reactor;
    }

    @Override
    public void run() {
        try {
            SocketChannel socketChannel = reactor.serverSocketChannel.accept();
            System.out.println("调用Handler来处理channel");
            if (socketChannel != null)//调用Handler来处理channel
                new SocketReadHandler(reactor.selector, socketChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}