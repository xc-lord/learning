package com.lord.dao;

import com.lord.model.User;

public interface IUserDao extends IGenericDao<User,Long> {
		
	/**
	 * 根据用户名，查找用户
	 * @param userName
	 * @return
	 */
	public User findByUserName(String userName);
}
