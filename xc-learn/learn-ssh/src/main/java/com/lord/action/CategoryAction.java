package com.lord.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lord.model.dto.CategoryTree;
import com.lord.service.ICategoryService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class CategoryAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private ICategoryService categoryService;
	private List<CategoryTree> categoryTrees;
	private String node;
	private String catalog;
	private String result;
	
	public ICategoryService getCategoryService() {
		return categoryService;
	}
	
	@Resource
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public List<CategoryTree> getCategoryTrees() {
		return categoryTrees;
	}

	public void setCategoryTrees(List<CategoryTree> categoryTrees) {
		this.categoryTrees = categoryTrees;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String showTree() {
		System.out.println("显示树的结构");
		categoryTrees = categoryService.listCategoryTree(catalog, node);
		try {
			result = JSONUtil.serialize(categoryTrees);
			if(StringUtils.isBlank(result) || result.equals("null")) {
				System.out.println("结果为空");
			}
			System.out.println("结果的大小：" + categoryTrees.size());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
