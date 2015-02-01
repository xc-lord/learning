package com.lord.dao.impl;

import com.lord.dao.IUserDao;
import com.lord.model.User;
import org.springframework.stereotype.Component;

/**
 * Created by xj_xiaocheng on 2015/1/30.
 */
@Component
public class UserDaoImpl extends GenericMybatisDaoImpl<User, Long> implements IUserDao {

}
