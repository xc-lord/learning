package com.lord.app;

import com.lord.dao.ICategoryDao;
import com.lord.mapping.UserMapper;
import com.lord.model.Category;
import com.lord.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * Created by xj_xiaocheng on 2015/1/27.
 */
public class MyBatisMain {

    /**
     * 获得MyBatis SqlSessionFactory
     * SqlSessionFactory负责创建SqlSession，一旦创建成功，就可以用SqlSession实例来执行映射语句，commit，rollback，close等方法。
     *
     * @return
     */
    private static SqlSessionFactory getSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String resource = "mybatis-config.xml";
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources
                    .getResourceAsReader(resource));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static void main(String[] args) {
        SqlSession sqlSession = getSessionFactory().openSession();

        ICategoryDao categoryDao = sqlSession.getMapper(ICategoryDao.class);
        Category category = categoryDao.get("1");

        System.out.println(category);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(1L);
        System.out.println(user.getUsername());

        sqlSession.close();
    }
}
