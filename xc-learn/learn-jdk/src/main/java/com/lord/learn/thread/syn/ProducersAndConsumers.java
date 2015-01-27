package com.lord.learn.thread.syn;

/** 
 *经典生产者与消费者问题：生产者不断的往仓库中存放产品，消费者从仓库中消费产品。 
 *其中生产者和消费者都可以有若干个。仓库容量有限，库满时不能存放，库空时不能取产品  
 */  
  
public class ProducersAndConsumers {  
    public static void main(String[] args) {  
        Storage storage = new Storage();  
        Thread consumer = new Thread(new Consumer(storage));  
        consumer.setName("消费者");  
        Thread producer = new Thread(new Producer(storage));  
        producer.setName("生产者");
        Thread producer2 = new Thread(new Producer(storage));  
        producer2.setName("生产者2");
        producer2.start();
        consumer.start();
        producer.start();  
    }  
}  
