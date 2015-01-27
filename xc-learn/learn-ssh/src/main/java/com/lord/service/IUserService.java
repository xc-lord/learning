package com.lord.service;

import java.util.List;

import com.lord.model.User;
import com.lord.model.dto.UserDTO;

public interface IUserService {
	
	/**
	 * 判断用户名是否存在
	 * @param userName
	 * @return
	 */
	boolean isExistUser(String userName);
	
	void save(User user);

	User loadUser(Long id);
	
	List<User> findAllUser();

	List<UserDTO> list();
}
