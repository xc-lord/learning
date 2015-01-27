package com.lord.learn.thread.syn;

public class Product {
	private String id;// 产品id   
    private String name;// 产品名称   
  
    public Product(String id, String name) {  
        this.id = id;  
        this.name = name;  
    }  
  
    @Override  
    public String toString() {  
        return "(产品ID：" + id + " 产品名称：" + name + ")";  
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}  
  
}
