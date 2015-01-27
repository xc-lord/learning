package com.lord.learn.design.proxy.dyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 功能: 动态代理
 */
public class DynProxyClient {
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		//创建目标对象，也就是被代理对象  
        ISubjectImpl realSubject = new ISubjectImpl();  
        //将目标对象交给代理  
        InvocationHandler handler = new DynamicProxy(realSubject);  
        
        //返回代理对象，相当于上面两句  
        ISubject subject = (ISubject) Proxy.newProxyInstance(handler.getClass().getClassLoader(),  
                realSubject.getClass().getInterfaces(),  
                handler);
          
        //叫代理对象去doSomething()，其实在代理对象中的doSomething()中还是会  
        //用handler来调用invoke(proxy, method, args) 参数proxy为调用者subject(this)，  
        //method为doSomething()，参数为方法要传入的参数，这里没有  
        
        System.out.println(subject.getMessage());
        subject.doSomething();
        subject.request();
        
	}
}
