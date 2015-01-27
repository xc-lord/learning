package com.lord.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lord.TestConfigure;
import com.lord.comm.AppConfig;
import com.lord.model.Catalog;
import com.lord.model.Category;

public class TestCatalogDao {
	
	@Test
	public void testCreatRootCatalog() {
		String rootName = "后台管理";
		ApplicationContext context = new ClassPathXmlApplicationContext(TestConfigure.APPLICATION_CONTEXT_XML);
		
		ICategoryDao categoryDao = context.getBean(ICategoryDao.class);
		Category category = new Category();
		category.setName(rootName + "根分类");
		category.setDescription(rootName + "根分类");
		categoryDao.save(category);
		
		
		ICatalogDao catalogDao = context.getBean(ICatalogDao.class);
		Catalog entity = new Catalog();
		entity.setId(AppConfig.commConfig.getManageRootCatalog());
		entity.setName(rootName + "根目录");
		entity.setDescription(rootName + "根目录");
		entity.setSequenceNo(1);
		entity.setCategory(category);
		
		catalogDao.save(entity);
	}
	
	@Test
	public void testAppConfig() {
		TestConfigure.getContext();
		System.out.println(AppConfig.commConfig.getManageRootCatalog());
	}
}
