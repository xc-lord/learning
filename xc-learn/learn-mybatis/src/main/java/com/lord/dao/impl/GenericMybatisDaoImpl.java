package com.lord.dao.impl;

import com.lord.dao.GenericMybatisDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by xj_xiaocheng on 2015/1/29.
 */
public class GenericMybatisDaoImpl<T, PK extends Serializable> implements GenericMybatisDao<T, PK> {

    protected Logger logger = LoggerFactory.getLogger(GenericMybatisDaoImpl.class);

    private SqlSessionTemplate sqlSessionTemplate;
    private Class<T> clazz;
    private String clazzName;

    public GenericMybatisDaoImpl() {
        // 通过范型反射，取得在子类中定义的class.
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        clazzName = clazz.getName();
        //clazzName = clazz.getSimpleName();
        logger.info("初始化DAO:" + clazzName);
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public String getClazzName() {
        return clazzName;
    }

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Resource
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public void insert(T entity) {

    }

    @Override
    public int update(T entity) {
        return 0;
    }

    @Override
    public int delete(PK primaryKey) {
        return 0;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public T get(PK primaryKey) {
        String queryName = getClazzName() + ".selectByPrimaryKey";
        logger.info("查询名称：" + queryName);
        return sqlSessionTemplate.selectOne(queryName, primaryKey);
    }

    @Override
    public List<T> select() {
        return null;
    }

    @Override
    public void batchInsert(List<T> list) {

    }

    @Override
    public void batchUpdate(List<T> list) {

    }

    @Override
    public void batchDelete(List<PK> list) {

    }
}
