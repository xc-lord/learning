package com.lord.mybatis;

import com.lord.comm.utils.Log4jUtils;
import com.lord.dao.IUserDao;
import com.lord.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xj_xiaocheng on 2015/1/30.
 */
public class TestGenericMybatis {

    private ApplicationContext context;
    private IUserDao userDao;

    @Before
    public void init() {
        System.out.println("测试Spring与Mybatis整合：泛型DAO");
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        org.apache.ibatis.logging.LogFactory.useLog4JLogging();
        userDao = context.getBean(IUserDao.class);
    }

    @Test
    public void get() {
        Log4jUtils.changeLoggerLevel("com.lord.dao.impl.GenericMybatisDaoImpl", "DEBUG");
        User user = userDao.get(1L);
        System.out.println(user.getUsername());
    }

    @Test
    public void listLogger() {
        System.out.println(Log4jUtils.listLoggerInfo("com.lord.dao"));
    }

    @Test
    public void insert() {
        User user = newUser();

        userDao.insert(user);
        System.out.println("生成的主键ID:" + user.getId());
    }

    private User newUser() {
        User user = new User();
        user.setEmail("miraclelord@163.com");
        user.setUsername("小李");
        user.setLevel(2);
        user.setMobile("13257598132");
        return user;
    }

    @Test
    public void batchInsert() {
        List<User> users = new ArrayList<User>();
        users.add(newUser());
        users.add(newUser());

        userDao.batchInsert(users);
    }

    @Test
    public void deleteBatch() {
        //userDao.delete(28L,27L);
        List<Long> list = new ArrayList<Long>();
        list.add(25L);
        list.add(26L);
        userDao.batchDelete(list);
    }

}
