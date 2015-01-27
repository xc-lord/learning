package com.lord.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lord.TestConfigure;

public class TestUserDao {

	@Test
	public void testCount() {
		ApplicationContext context = new ClassPathXmlApplicationContext(TestConfigure.APPLICATION_CONTEXT_XML);
		IUserDao userDao = context.getBean(IUserDao.class);
		System.out.println(userDao.countBy("userName", "洪楚彬"));
	}
}
