package com.lord.service;

import org.junit.Test;

public class TestMain {

	
	@Test
	public void test() {
		int total = 40, pageSize = 9, page;
		double s = 40.0 / 9.0;
		page = total / pageSize;
		System.out.println(s);
		
		if(page*pageSize < total) {
			page++;
		}
		
		System.out.println(page);
		
		System.out.println(Math.ceil(s));
		System.out.println(Math.ceil(total / pageSize));
		
		System.out.println(getPage(total, pageSize));
		
	}
	
	public int getPage(int total, int pageSize) {
		int page = total / pageSize;
		if(page*pageSize < total) {
			page++;
		}
		return page;
	}
	
}
