package com.lord.dao.impl;

import com.lord.dao.GenericMybatisDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by xj_xiaocheng on 2015/1/29.
 */
public class GenericMybatisDaoImpl<T, PK extends Serializable> implements GenericMybatisDao<T, PK> {

    public static final String INSERT_SELECTIVE = ".insertSelective";
    public static final String SELECT_BY_PRIMARY_KEY = ".selectByPrimaryKey";
    public static final String UPDATE_BY_PRIMARY_KEY_SELECTIVE = ".updateByPrimaryKeySelective";
    public static final String DELETE_BY_PRIMARY_KEY = ".deleteByPrimaryKey";
    public static final String DELETE_BATCH = ".deleteBatch";
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
        if(entity == null) {
            logger.warn("添加数据失败，对象为空！");
            return;
        }
        sqlSessionTemplate.insert(getClazzName() + INSERT_SELECTIVE, entity);
    }

    @Override
    public int update(T entity) {
        if(entity == null) {
            logger.warn("数据更新失败，对象为空！");
            return 0;
        }
        return sqlSessionTemplate.update(getClazzName() + UPDATE_BY_PRIMARY_KEY_SELECTIVE, entity);
    }

    @Override
    public int delete(PK... primaryKey) {
        if(primaryKey == null) {
            logger.warn("数据删除失败，主键为空！");
            return 0;
        }
        return sqlSessionTemplate.delete(getClazzName() + DELETE_BATCH, primaryKey);
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public T get(PK primaryKey) {
        if(primaryKey == null) {
            logger.warn("数据查询失败，主键为空！");
            return null;
        }
        logger.debug("根据主键获取数据！");
        return sqlSessionTemplate.selectOne(getClazzName() + SELECT_BY_PRIMARY_KEY, primaryKey);
    }

    @Override
    public List<T> select() {
        return null;
    }

    @Override
    public void batchInsert(List<T> list) {
        if(list == null || list.size() < 1) {
            logger.warn("批量写入数据失败，数据为空！");
            return;
        }
        sqlSessionTemplate.insert(getClazzName() + ".insertBatch", list);
    }

    @Override
    public void batchUpdate(List<T> list) {
        if(list == null || list.size() < 1) {
            logger.warn("批量更新数据失败，数据为空！");
            return;
        }
        for (T t : list) {
            update(t);
        }
    }

    @Override
    public int batchDelete(List<PK> list) {
        if(list == null || list.size() < 1) {
            logger.warn("批量删除数据失败，数据为空！");
            return 0;
        }
        return sqlSessionTemplate.delete(getClazzName() + DELETE_BATCH, list.toArray());
    }

    @Override
    public List<T> query(T entity) {
        return null;
    }

    @Override
    public T queryOne(T entity) {
        return null;
    }
}
