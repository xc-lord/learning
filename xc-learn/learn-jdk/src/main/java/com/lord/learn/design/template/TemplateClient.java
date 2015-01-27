package com.lord.learn.design.template;

public class TemplateClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractTemplate template;
		template = new ConcreteClassA();
		template.templateMethod();
		
		template = new ConcreteClassB();
		template.templateMethod();		
	}

}
