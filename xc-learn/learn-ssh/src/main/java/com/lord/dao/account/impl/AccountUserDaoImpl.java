package com.lord.dao.account.impl;

import org.springframework.stereotype.Component;

import com.lord.dao.account.IAccountUserDao;
import com.lord.dao.impl.GenericDaoImpl;
import com.lord.model.account.AccountUser;

@Component
public class AccountUserDaoImpl extends GenericDaoImpl<AccountUser, Long> implements IAccountUserDao {


}
