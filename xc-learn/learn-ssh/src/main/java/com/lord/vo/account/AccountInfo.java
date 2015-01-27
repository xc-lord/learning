package com.lord.vo.account;

import com.lord.model.account.AccountRecord;
import com.lord.model.account.AccountUser;

public class AccountInfo {
	private AccountUser accountUser;
	private AccountRecord record;
	private String type;
	
	public AccountUser getAccountUser() {
		return accountUser;
	}
	public void setAccountUser(AccountUser accountUser) {
		this.accountUser = accountUser;
	}
	public AccountRecord getRecord() {
		return record;
	}
	public void setRecord(AccountRecord record) {
		this.record = record;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
