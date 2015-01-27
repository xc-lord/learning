package com.lord.learn.design.composite;

import java.util.ArrayList;
import java.util.List;

public class ConcreteCompany extends Company {
	private List<Company> children = new ArrayList<Company>();

	public ConcreteCompany(String name) {
		super(name);
	}

	@Override
	public void add(Company c) {
		children.add(c);
	}

	@Override
	public void display(int depth) {
		StringBuffer str = new StringBuffer("-");
		for (int i = 0; i < depth; i++) {
			str.append("-");
		}
		System.out.println(str + this.name);
		for (Company c : children) {
			c.display(depth + 2);
		}
	}

	@Override
	public void lineOfDuty() {
		for (Company c : children) {
			c.lineOfDuty();
		}
	}

	@Override
	public void remove(Company c) {
		children.remove(c);
	}

}
