package com.lord.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.lord.dao.IUserDao;
import com.lord.model.User;
import com.lord.model.dto.UserDTO;
import com.lord.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	private IUserDao userDao;

	@Override
	public boolean isExistUser(String userName) {
		User user = userDao.findByUserName(userName);
		if(user != null) {
			return true;
		}
		return false;
	}

	@Override
	public void save(User user) {
		if(user == null) {
			throw new RuntimeException("save() user 不能为空");
		}
		if(StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword())){
			throw new RuntimeException("save() 用户名和密码不能为空");
		}			
		userDao.save(user);
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User loadUser(Long id) {
		return this.userDao.get(id);
	}

	@Override
	public List<User> findAllUser() {
		return this.userDao.qreryAll();
	}

	@Override
	public List<UserDTO> list() {
		List<User> users = findAllUser();
		List<UserDTO> list = new ArrayList<UserDTO>();
		for (User user : users) {
			UserDTO dto = new UserDTO(user);
			list.add(dto);
		}
		return list;
	}
	
}
