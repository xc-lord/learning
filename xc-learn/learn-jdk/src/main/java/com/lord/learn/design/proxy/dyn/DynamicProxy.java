package com.lord.learn.design.proxy.dyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
	private Object object;  
    public DynamicProxy(Object object) {  
        this.object = object;  
    }  
  
    @Override  
    public Object invoke(Object proxy, Method method, Object[] args)  
            throws Throwable {
    	String methodName = method.getName();
        System.out.println("Before Invoke ! method : " + method);  
          
        //我们可以再代理方法调用前后添加功能  
        Object result = method.invoke(object, args);
        if(methodName.equals("getMessage")) {
        	result = "代理返回值";
        }
        
        
        System.out.println("After Invoke !");  
        return result;  
    }  
}
