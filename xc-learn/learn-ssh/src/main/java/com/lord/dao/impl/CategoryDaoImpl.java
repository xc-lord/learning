package com.lord.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lord.dao.ICatalogDao;
import com.lord.dao.ICategoryDao;
import com.lord.model.Category;

@Component
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements ICategoryDao {
	private ICatalogDao catalogDao;

	public ICatalogDao getCatalogDao() {
		return catalogDao;
	}

	@Resource
	public void setCatalogDao(ICatalogDao catalogDao) {
		this.catalogDao = catalogDao;
	}
	
	@Override
	public Category findRootCategory(String catalogName) {
		return catalogDao.getCategory(catalogName);
	}

	@Override
	public List<Category> findChildCategory(Category category) {
		return findChildCategory(category.getId());
	}

	@Override
	public List<Category> findChildCategory(Long id) {
		return this.query("from Category c where c.parent.id=? order by c.sequenceNo", new Object[]{id});
	}
	
}
