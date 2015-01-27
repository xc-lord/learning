package com.lord.service;

import java.util.List;

import com.lord.model.Category;
import com.lord.model.dto.CategoryTree;

public interface ICategoryService {

	/**
	 * 保存分类
	 * @param category
	 */
	void save(Category category);
	
	/**
	 * 根据父分类ID，查找所有子分类
	 * @param id	父分类ID
	 * @return		所有子分类
	 */
	List<Category> findChildCategory(Long id);
	
	List<CategoryTree> listCategoryTree(String catalog, String id);
	
}
