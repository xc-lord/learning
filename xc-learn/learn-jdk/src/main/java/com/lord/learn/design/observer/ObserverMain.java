package com.lord.learn.design.observer;

/**
 * 观察者模式
 * 类似JMS的消息订阅与消费，例如提交了一个订单之后，系统需要给用户发邮件、提醒客服发货、会记开发票
 * Created by xj_xiaocheng on 2015/1/19.
 */
public class ObserverMain {

    public static void main(String[] args) {
        AbstractMessage message = new AddOrderMessageImpl();
        message.add(new ObserverEmailImpl());
        message.add(new ObserverServerImpl());
        message.add(new ObserverAccountantImpl());

        message.send();

        message.send("用户提交订单后，");
    }

}
