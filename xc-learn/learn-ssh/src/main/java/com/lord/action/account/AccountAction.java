package com.lord.action.account;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lord.comm.page.PageStyle;
import com.lord.comm.page.Pager;
import com.lord.model.account.AccountRecord;
import com.lord.model.account.AccountUser;
import com.lord.service.account.IAccountService;
import com.lord.vo.account.AccountInfo;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class AccountAction extends ActionSupport implements ServletRequestAware {
	
	protected Logger logger = LoggerFactory.getLogger(AccountAction.class);

	private static final long serialVersionUID = 1L;
	
	private List<AccountInfo> accountInfos;
	private Long accountId;
	private Long traderId;
	private List<Long> joinAccountIds;
	private Double amount;
	private Boolean succeed;
	private Date nowTime = new Date();
	private AccountUser accountUser;
	private List<AccountRecord> records;
	private Integer expenseType;
	private Pager pager = new Pager();
	private HttpServletRequest request;
	
	private IAccountService accountService;
	
	@Resource
	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public String listAccount() {
		accountInfos = accountService.listAccountInfo();
		return SUCCESS;
	}
	
	public String keepAccount() {
		if(amount != null && accountId != null && joinAccountIds != null) {
			succeed = accountService.keepAccount(accountId, joinAccountIds, amount);
			logger.info("记帐amount=" + amount + " 结果：" + succeed);
		}
		return listAccount();
	}
	
	public String settleAccounts() {
		if(accountId != null) {			
			accountUser = accountService.load(accountId);
		}
		if(amount != null && accountId != null && joinAccountIds != null) {
			succeed = accountService.keepAccount(accountId, joinAccountIds, amount);
			return "submitSuccess";
		}
		accountInfos = accountService.listAccountInfo();
		return SUCCESS;
	}
	
	public String accountDetail() {
		if(accountId != null) {
			
			accountUser = accountService.load(accountId);
			Long totalRows = accountService.countRecord(accountUser, expenseType);
			pager.setTotalRows(totalRows);
			pager.setPageSize(10);
			pager.setRequest(request);
			pager.setPageStyle(new PageStyle());
			pager.init();
			records = accountService.listRecords(accountUser, expenseType, pager.getStartRow(), pager.getPageSize());
			return SUCCESS;
		}
		return SUCCESS;
	}
	
	public List<AccountInfo> getAccountInfos() {
		return accountInfos;
	}

	public void setAccountInfos(List<AccountInfo> accountInfos) {
		this.accountInfos = accountInfos;
	}

	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public List<Long> getJoinAccountIds() {
		return joinAccountIds;
	}
	public void setJoinAccountIds(List<Long> joinAccountIds) {
		this.joinAccountIds = joinAccountIds;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Boolean getSucceed() {
		return succeed;
	}

	public void setSucceed(Boolean succeed) {
		this.succeed = succeed;
	}

	public Date getNowTime() {
		return nowTime;
	}

	public void setNowTime(Date nowTime) {
		this.nowTime = nowTime;
	}

	public List<AccountRecord> getRecords() {
		return records;
	}

	public void setRecords(List<AccountRecord> records) {
		this.records = records;
	}

	public Integer getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(Integer expenseType) {
		this.expenseType = expenseType;
	}

	public AccountUser getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(AccountUser accountUser) {
		this.accountUser = accountUser;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public Long getTraderId() {
		return traderId;
	}

	public void setTraderId(Long traderId) {
		this.traderId = traderId;
	}

}
