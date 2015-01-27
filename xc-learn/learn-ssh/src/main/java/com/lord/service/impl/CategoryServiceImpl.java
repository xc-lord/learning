package com.lord.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.lord.dao.ICategoryDao;
import com.lord.model.Category;
import com.lord.model.dto.CategoryTree;
import com.lord.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {
	private ICategoryDao categoryDao;
	
	public ICategoryDao getCategoryDao() {
		return categoryDao;
	}
	
	@Resource
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public void save(Category category) {
		categoryDao.save(category);		
	}

	@Override
	public List<Category> findChildCategory(Long id) {
		return categoryDao.findChildCategory(id);
	}

	@Override
	public List<CategoryTree> listCategoryTree(String catalog, String id) {
		List<Category> cats = null;
		if(StringUtils.equals(id, "root") && StringUtils.isNotBlank(catalog)) {
			Category category = categoryDao.findRootCategory(catalog);
			//如果找不到根本分类，返回空
			if(category == null)
				return null;
			cats = categoryDao.findChildCategory(category);			
		} else {
			cats = categoryDao.findChildCategory(Long.parseLong(id));
		}
		if(cats == null || cats.size() < 1)
			return null;
		
		List<CategoryTree> list = new ArrayList<CategoryTree>();
		for (Category category : cats) {
			CategoryTree tree = new CategoryTree(category);
			list.add(tree);
		}
		return list;
	}

}
