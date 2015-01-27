package com.lord.service.account;

import java.util.List;

import com.lord.model.account.AccountRecord;
import com.lord.model.account.AccountUser;
import com.lord.vo.account.AccountInfo;

public interface IAccountService {
	/** 默认的帐户类型：北京国美 */
	public static final String USER_TYPE_DEFAULT = "北京国美";
	/** 交易类型：消费 */
	public static final int EXPENSE_COST = 1;
	/** 交易类型：参与消费 */
	public static final int EXPENSE_COST_JOIN = -1;
	/** 交易类型：还款 */
	public static final int EXPENSE_REFUND = -2;
	/** 交易类型：收款 */
	public static final int EXPENSE_REFUND_BE = 2;
	
	/**
	 * 添加帐户的用户
	 * @param name	用户名
	 */
	void addAccountUser(String name);
	
	/**
	 * 添加帐户的用户
	 * @param name	用户名
	 * @param type	类型
	 */
	void addAccountUser(String name, String type);
	
	/**
	 * 记帐功能
	 * @param accountId			出资人
	 * @param joinAccountIds	参与人
	 * @param amount			消费金额
	 */
	boolean keepAccount(Long accountId, List<Long> joinAccountIds, Double amount);
	
	/**
	 * 结账功能
	 * @param accountId		还款人
	 * @param traderId		收款人
	 * @param amount		还款金额
	 */
	void settleAccount(Long accountId, Long traderId, Double amount);
	
	/**
	 * 查找出当前所有的账目信息
	 * @return
	 */
	List<AccountInfo> listAccountInfo();
	
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
	 * 根据ID,获得一个帐户用户
	 * @param accountId
	 * @return
	 */
	AccountUser load(Long accountId);

	/**
	 * 获得用户交易记录的总条数
	 * @param accountUser	帐户用户
	 * @param expenseType	交易类型
	 * @return
	 */
	Long countRecord(AccountUser accountUser, Integer expenseType);
}
