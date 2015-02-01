package com.lord.app;

import com.lord.dao.IUserDao;
import com.lord.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xj_xiaocheng on 2015/1/30.
 */
public class GenericMybatisMain {

    public static void main(String[] args) {
        System.out.println("测试Spring与Mybatis整合");
        ApplicationContext ctx = null;
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserDao userDao = ctx.getBean(IUserDao.class);

        User user = userDao.get(1L);

        System.out.println(user.getUsername());
    }
}
