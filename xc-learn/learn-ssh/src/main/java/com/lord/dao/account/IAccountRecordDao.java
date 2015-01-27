package com.lord.dao.account;

import java.util.List;

import com.lord.dao.IGenericDao;
import com.lord.model.account.AccountRecord;
import com.lord.model.account.AccountUser;

public interface IAccountRecordDao extends IGenericDao<AccountRecord, Long> {

	/**
	 * 查出一个帐户的最近交易
	 * @param accountUser
	 * @return
	 */
	AccountRecord findRecently(AccountUser accountUser);
	
	/**
	 * 查询出一个用户的交易明细记录
	 * @param accountUser	用户帐号
	 * @param expenseType	交易类型
	 * @param start			记录开始条数
	 * @param howMany		结果大小
	 * @return
	 */
	List<AccountRecord> listRecords(AccountUser accountUser, Integer expenseType, Integer start, Integer howMany);

	/**
	 * 获得用户交易记录的总条数
	 * @param accountUser	帐户用户
	 * @param expenseType	交易类型
	 * @return
	 */
	Long countRecord(AccountUser accountUser, Integer expenseType);

}
