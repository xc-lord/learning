package com.lord.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lord.TestConfigure;
import com.lord.model.Category;

public class TestCategoryDao {

	@Test
	public void testSave() {
		ApplicationContext context = new ClassPathXmlApplicationContext(TestConfigure.APPLICATION_CONTEXT_XML);
		ICategoryDao categoryDao = context.getBean(ICategoryDao.class);
		Category entity = new Category();
		entity.setName("手机");
		entity.setParent(null);
		
		categoryDao.save(entity);
		
		Category entity2 = new Category();
		entity2.setName("三星手机");
		entity2.setParent(entity);
		categoryDao.save(entity2);
	}

	@Test
	public void testAddChild() {
		ApplicationContext context = new ClassPathXmlApplicationContext(TestConfigure.APPLICATION_CONTEXT_XML);
		ICategoryDao categoryDao = context.getBean(ICategoryDao.class);
		
		//Category category = categoryDao.findRootCategory(AppConfig.commConfig.getManageRootCatalog());
		Category category = categoryDao.get(2L);
		
		System.out.println(category);
		
		for (int i = 0; i < 3; i++) {
			Category cat = new Category();
			cat.setName(category.getName() + "子分类" + i);
			cat.setParent(category);
			cat.setSequenceNo(i);
			categoryDao.save(cat);
		}
	}
	
	@Test
	public void testQueryChild() {
		ApplicationContext context = new ClassPathXmlApplicationContext(TestConfigure.APPLICATION_CONTEXT_XML);
		ICategoryDao categoryDao = context.getBean(ICategoryDao.class);
		
		//Category category = categoryDao.findRootCategory(AppConfig.commConfig.getManageRootCatalog());
		Category category = categoryDao.get(2L);
		List<Category> list = categoryDao.findChildCategory(category);
		
		for (Category cat : list) {
			System.out.println(cat);
		}
		
	}
}
