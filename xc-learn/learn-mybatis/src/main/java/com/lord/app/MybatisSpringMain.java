package com.lord.app;

import com.lord.mapping.UserMapper;
import com.lord.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xj_xiaocheng on 2015/1/29.
 */
public class MybatisSpringMain {

    public static void main(String[] args) {
        System.out.println("测试Spring与Mybatis整合");
        ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper=(UserMapper)ctx.getBean("userMapper");
        User  u = userMapper.selectByPrimaryKey(1L);
        System.out.println("用户名：" + u.getUsername());
    }
}
