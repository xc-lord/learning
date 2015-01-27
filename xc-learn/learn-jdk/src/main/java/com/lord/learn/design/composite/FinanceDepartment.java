package com.lord.learn.design.composite;

public class FinanceDepartment extends Company {

	public FinanceDepartment(String name) {
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
		System.out.println(this.name + " 公司财务收支管理");
	}

	@Override
	public void remove(Company c) {
		
	}

}
