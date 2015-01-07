package com.lord.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring相关工具类，初始化Spring实例
 * @author xj_xiaocheng
 *
 */
public class SpringTools {
	protected static Logger log = LoggerFactory.getLogger(SpringTools.class);
	
	private ApplicationContext context;

	/**
	 * 只有被调用时才会加载
	 * @author xj_xiaocheng
	 *
	 */
	private static class SingletionHolder {
		private static SpringTools instance = new SpringTools();
	}
	
	private SpringTools() {
		log.info("init spring context");
		context = new ClassPathXmlApplicationContext("application.xml");
	}
	
	public static ApplicationContext getContext() {
		return SingletionHolder.instance.context;
	}

}