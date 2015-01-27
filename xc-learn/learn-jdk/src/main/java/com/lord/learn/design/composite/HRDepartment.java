package com.lord.learn.design.composite;

public class HRDepartment extends Company {

	public HRDepartment(String name) {
		super(name);
	}

	@Override
	public void add(Company c) {
	}

	@Override
	public void display(int depth) {
		StringBuffer str = new StringBuffer("-");
		for (int i = 0; i < depth; i++) {
			str.append("-");
		}
		System.out.println(str + this.name);
	}

	@Override
	public void lineOfDuty() {
		System.out.println(this.name + " 员工招聘培训管理");
	}

	@Override
	public void remove(Company c) {
		
	}

}
