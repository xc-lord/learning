package com.lord.model.dto;

import com.lord.model.Category;

public class CategoryTree {
	private String id;
	private String text;
	private boolean leaf;
	private String cls;
	
	public CategoryTree() {
	}
	public CategoryTree(Category category) {
		this.id = category.getId().toString();
		this.text = category.getName();
		this.leaf = category.isLeaf();
		this.cls = category.getCls();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	
}
