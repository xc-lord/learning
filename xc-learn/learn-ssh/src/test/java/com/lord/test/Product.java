package com.lord.test;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Product {
	private String id;
	private String productId;
	private String displayName;
	@OneToOne
	private Item1 item1;
	@OneToOne
	private Item2 item2;
	@OneToOne
	private Item3 item3;

}
