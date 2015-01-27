package com.lord.model;

import java.util.Date;

/**
 * 文件基本属性
 */
public class FileProperty {
	private String name;//文件名称
	private String dir;//文件目录
	private Date modifTime;//上次修改时间
	private Date createTime;//创建时间
	private String size;//文件大小
	private String suffix;//后缀
	private String type;//类型
	
	public FileProperty(){}
	
	public FileProperty(String name, String dir) {
		super();
		this.name = name;
		this.dir = dir;
	}

	@Override
	public String toString() {
		return "FileProperty [name=" + name + ", dir=" + dir + "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public Date getModifTime() {
		return modifTime;
	}
	public void setModifTime(Date modifTime) {
		this.modifTime = modifTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
