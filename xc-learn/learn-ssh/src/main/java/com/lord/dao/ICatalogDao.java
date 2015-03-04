package com.lord.dao;

import com.lord.model.Catalog;
import com.lord.model.Category;

public interface ICatalogDao extends IGenericDao<Catalog, String> {

	/**
	 * 根据目录ID，查找对应根分类
	 * @param id
	 * @return
	 */
	Category getCategory(String id);
}
