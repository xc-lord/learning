package com.lord.dao.impl;

import org.springframework.stereotype.Component;

import com.lord.dao.ICatalogDao;
import com.lord.model.Catalog;
import com.lord.model.Category;

@Component
public class CatalogDaoImpl extends GenericDaoImpl<Catalog, String> implements ICatalogDao {

	@Override
	public Category getCategory(String id) {
		Catalog catalog = this.get(id);
		if(catalog == null) 
			return null;
		return catalog.getCategory();
	}

}
