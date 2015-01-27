package com.lord.learn.thread.syn;

/** 
 * 消费者 
 */  
class Consumer implements Runnable {  
    private Storage storage;  
  
    public Consumer(Storage storage) {  
        this.storage = storage;  
    }  
  
    @Override  
    public void run() {
    	while(true) {
    		storage.pop();
    	}
    }  
}  
