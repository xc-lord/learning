package com.lord.learn.thread.syn;

/** 
 * 生产者 
 */  
class Producer implements Runnable {  
    private Storage storage;  
  
    public Producer(Storage storage) {  
        this.storage = storage;  
    }  
  
    @Override  
    public void run() {
    	for (int i = 0; i < 100; i++) {
    		Product product = new Product("090505105-" + i, "电话:" + i);  
    		storage.push(product);
		}
    }  
  
}  