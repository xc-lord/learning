package com.lord.dao.impl;

import org.springframework.stereotype.Component;

import com.lord.dao.IUserDao;
import com.lord.model.User;

@Component
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements IUserDao {
	
	@Override
	public User findByUserName(String userName) {
		return getBy("userName", userName);
	}

}
