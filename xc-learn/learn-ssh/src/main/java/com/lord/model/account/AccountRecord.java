package com.lord.model.account;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 功能: 交易记录
 */
@Entity
public class AccountRecord {
	private Long id;
	private AccountUser accountUser;//产生交易的用户
	private double amount;//交易实际金额
	private double totalAmount;//交易总金额
	private int expenseType;//交易类型
	private Date createTime;//交易时间
	private AccountUser trader;//交易的对象
	private String joinNames;//交易的参与人
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	public AccountUser getAccountUser() {
		return accountUser;
	}
	public void setAccountUser(AccountUser accountUser) {
		this.accountUser = accountUser;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(int expenseType) {
		this.expenseType = expenseType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@ManyToOne
	public AccountUser getTrader() {
		return trader;
	}
	public void setTrader(AccountUser trader) {
		this.trader = trader;
	}
	public String getJoinNames() {
		return joinNames;
	}
	public void setJoinNames(String joinNames) {
		this.joinNames = joinNames;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
