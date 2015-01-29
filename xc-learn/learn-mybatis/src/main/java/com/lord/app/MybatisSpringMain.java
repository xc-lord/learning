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
        ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper=(UserMapper)ctx.getBean("userMapper");
        User  u = userMapper.selectByPrimaryKey(1L);
        System.out.println(u.getUsername());
    }
}
