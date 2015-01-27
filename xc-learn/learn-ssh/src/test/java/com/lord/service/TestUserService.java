package com.lord.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lord.TestConfigure;
import com.lord.model.User;
import com.lord.utils.NumberUtils;

public class TestUserService {

	@Test
	public void testQuery() {
		ApplicationContext context = new ClassPathXmlApplicationContext(TestConfigure.APPLICATION_CONTEXT_XML);
		IUserService userService = context.getBean(IUserService.class);
		List<User> users = userService.findAllUser();
		for (User user : users) {
			System.out.println(user.getUserName());
		}
	}
	
	@Test
	public void testSave() {
		ApplicationContext context = new ClassPathXmlApplicationContext(TestConfigure.APPLICATION_CONTEXT_XML);
		IUserService userService = context.getBean(IUserService.class);
		User user = new User();
		user.setUserName("蓝齐延" + NumberUtils.randomNum(1, 20));
		user.setPassword("835shg");
		user.setMobile("110112");
		userService.save(user);
	}
	
	@Test
	public void test() {
		String hql = "select * from User where username=? and password= ?";
		String s = "?";
		
		System.out.println(StringUtils.countMatches(hql, s));
	}
	
}
