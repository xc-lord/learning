package com.lord;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConfigure {
	private static ApplicationContext context;
	public static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";
	
	public static ApplicationContext getContext() {
		if(context == null)
			context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
		return context;
	}
}
