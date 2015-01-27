package com.lord.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lord.TestConfigure;
import com.lord.service.account.IAccountService;

public class TestAccountService {
	private ApplicationContext context;
	
	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext(TestConfigure.APPLICATION_CONTEXT_XML);
	}

	@Test
	public void testKeepAccount() {
		IAccountService accountService = context.getBean(IAccountService.class);
		Long accountId = 1L;
		Double amount = 55.0;
		List<Long> joinAccountIds = new ArrayList<Long>();
		joinAccountIds.add(1L);
		joinAccountIds.add(2L);
		joinAccountIds.add(3L);
		joinAccountIds.add(4L);
		joinAccountIds.add(6L);
		
		accountService.keepAccount(accountId, joinAccountIds, amount);
	}
	
	@Test
	public void testListAccountInfo() {
		IAccountService accountService = context.getBean(IAccountService.class);		
		accountService.listAccountInfo();
	}
	
	@Test
	public void testAddAccountUser() {
		IAccountService accountService = context.getBean(IAccountService.class);
		accountService.addAccountUser("肖诚");
		accountService.addAccountUser("洪楚彬");
		accountService.addAccountUser("袁明华");
		accountService.addAccountUser("乔力");
		accountService.addAccountUser("查从武");
		accountService.addAccountUser("陈悦仁");
	}
}
