package com.lord.dao;

import java.util.List;

import com.lord.model.Category;

public interface ICategoryDao extends IGenericDao<Category, Long> {

	/**
	 * 根据目录ID，查找对应的根分类
	 * @param catalogName
	 * @return
	 */
	Category findRootCategory(String catalogName);

	/**
	 * 根据父分类，查找所有子分类
	 * @param category	父分类
	 * @return
	 */
	List<Category> findChildCategory(Category category);
	
	/**
	 * 根据父分类ID，查找所有子分类
	 * @param id	父分类ID
	 * @return
	 */
	List<Category> findChildCategory(Long id);

}
