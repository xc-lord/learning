package com.lord.dao.account.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lord.dao.account.IAccountRecordDao;
import com.lord.dao.impl.GenericDaoImpl;
import com.lord.model.account.AccountRecord;
import com.lord.model.account.AccountUser;

@Component
public class AccountRecordDaoImpl extends GenericDaoImpl<AccountRecord, Long> implements IAccountRecordDao {

	@Override
	public AccountRecord findRecently(AccountUser accountUser) {
		String hql = "from " + getClassName() + " where accountUser=? order by createTime desc";
		return queryOne(hql, new Object[]{accountUser});
	}

	@Override
	public List<AccountRecord> listRecords(AccountUser accountUser,	Integer expenseType, Integer start, Integer howMany) {
		List<AccountRecord> accountRecords = new ArrayList<AccountRecord>();
		String hql = "from " + getClassName() + " where accountUser=? and expenseType=? order by createTime desc";
		Object[] params = new Object[]{accountUser, expenseType};
		
		if(expenseType == null) {
			hql = "from " + getClassName() + " where accountUser=? order by createTime desc";
			params = new Object[]{accountUser};
		}
		
		if(howMany != null && start != null)
			accountRecords = query(hql, params, start, howMany);
		else if(howMany == null && (start == null || start < 0)) {
			accountRecords = query(hql, params, 0, 20);
		}
		
		return accountRecords;
	}

	@Override
	public Long countRecord(AccountUser accountUser, Integer expenseType) {
		String hql = "select count(*) from " + getClassName() + " where accountUser=? and expenseType=?";
		Object[] params = new Object[]{accountUser, expenseType};
		
		if(expenseType == null) {
			hql = "select count(*) from " + getClassName() + " where accountUser=?";
			params = new Object[]{accountUser};
		}
		return count(hql, params);
	}


}
