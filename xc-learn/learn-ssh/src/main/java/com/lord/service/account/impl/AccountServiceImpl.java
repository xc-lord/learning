package com.lord.service.account.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lord.dao.account.IAccountRecordDao;
import com.lord.dao.account.IAccountUserDao;
import com.lord.model.account.AccountRecord;
import com.lord.model.account.AccountUser;
import com.lord.service.account.IAccountService;
import com.lord.vo.account.AccountInfo;

@Service
public class AccountServiceImpl implements IAccountService {
	protected static Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	private IAccountUserDao accountUserDao;
	private IAccountRecordDao accountRecordDao;

	public IAccountUserDao getAccountUserDao() {
		return accountUserDao;
	}
	
	@Resource
	public void setAccountUserDao(IAccountUserDao accountUserDao) {
		this.accountUserDao = accountUserDao;
	}

	public IAccountRecordDao getAccountRecordDao() {
		return accountRecordDao;
	}

	@Resource
	public void setAccountRecordDao(IAccountRecordDao accountRecordDao) {
		this.accountRecordDao = accountRecordDao;
	}

	@Override
	public void addAccountUser(String name, String type) {
		AccountUser user = new AccountUser();
		user.setName(name);
		user.setType(type);		
		accountUserDao.save(user);
	}

	@Override
	public boolean keepAccount(Long accountId, List<Long> joinAccountIds, Double amount) {
		AccountUser accountUser = accountUserDao.load(accountId);
		if(accountUser == null) {
			log.warn("记帐失败，出资人:" + accountId + "在系统里找不到对应的用户。");
			return false;
		}
		
		if(joinAccountIds == null || joinAccountIds.size() < 1) {
			log.warn("记帐失败，参与者:" + joinAccountIds + "的数量少于1。");
			return false;
		}
		
		boolean isInclude = false;
		String joinNames = accountUser.getName();
		List<AccountUser> joinUsers = new ArrayList<AccountUser>();
		for (Long joinAccountId : joinAccountIds) {
			if(accountId.equals(joinAccountId)) {
				isInclude = true;
				continue;
			}
			AccountUser joinUser = accountUserDao.load(joinAccountId);
			if(joinUser != null) {
				joinUsers.add(joinUser);
				joinNames += "，" + joinUser.getName();
			}
		}
		
		int joinUserSize = joinAccountIds.size();
		if(isInclude)
			joinUserSize--;
		if(joinUsers.size() != joinUserSize) {
			log.warn("记帐失败，参与者:" + joinAccountIds + "中，有在系统里找不到对应用户的编号。");
			return false;
		}
		
		Double joinAmount = -amount/(joinUserSize + 1);
		Date createTime = new Date();		
		for (AccountUser joinUser : joinUsers) {
			AccountRecord joinRecord = new AccountRecord();
			joinRecord.setAccountUser(joinUser);
			joinRecord.setAmount(joinAmount);
			joinRecord.setCreateTime(createTime);
			joinRecord.setExpenseType(EXPENSE_COST_JOIN);
			joinRecord.setJoinNames(joinNames);
			joinRecord.setTrader(accountUser);
			joinRecord.setTotalAmount(amount);
			accountRecordDao.save(joinRecord);
			
			joinUser.setBalance(joinUser.getBalance() + joinAmount);
			accountUserDao.update(joinUser);
		}
		
		double recordAmount = amount + joinAmount;
		AccountRecord record = new AccountRecord();
		record.setAccountUser(accountUser);
		record.setAmount(recordAmount);
		record.setCreateTime(createTime);
		record.setExpenseType(EXPENSE_COST);
		record.setJoinNames(joinNames);
		record.setTotalAmount(amount);		
		accountRecordDao.save(record);
		
		accountUser.setBalance(accountUser.getBalance() + recordAmount);
		accountUserDao.update(accountUser);
		return true;
	}

	@Override
	public void settleAccount(Long accountId, Long traderId, Double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AccountInfo> listAccountInfo() {
		List<AccountInfo> accountInfos = new ArrayList<AccountInfo>();
		
		List<AccountUser> accountUsers = accountUserDao.queryBy("type", USER_TYPE_DEFAULT);
		for (AccountUser accountUser : accountUsers) {
			AccountRecord record = accountRecordDao.findRecently(accountUser);
			AccountInfo info = new AccountInfo();
			info.setAccountUser(accountUser);
			info.setRecord(record);
			info.setType(typeToDesc(record));
			
			accountInfos.add(info);
		}
		
		return accountInfos;
	}

	private String typeToDesc(AccountRecord record) {
		if(record == null)
			return "暂无类型";
		
		if(EXPENSE_COST == record.getExpenseType())
			return "消费出资";
		else if(EXPENSE_COST_JOIN == record.getExpenseType())
			return "参与消费";
		else if(EXPENSE_REFUND == record.getExpenseType())
			return "结账还款";
		else
			return "结账收款";
	}

	@Override
	public List<AccountRecord> listRecords(AccountUser accountUser,	Integer expenseType, Integer start, Integer howMany) {
		return accountRecordDao.listRecords(accountUser, expenseType, start, howMany);
	}

	@Override
	public void addAccountUser(String name) {
		addAccountUser(name, IAccountService.USER_TYPE_DEFAULT);		
	}

	@Override
	public AccountUser load(Long accountId) {
		return accountUserDao.get(accountId);
	}

	@Override
	public Long countRecord(AccountUser accountUser, Integer expenseType) {
		return accountRecordDao.countRecord(accountUser, expenseType);
	}

}
