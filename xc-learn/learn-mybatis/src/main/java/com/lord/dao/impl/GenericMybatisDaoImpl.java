package com.lord.dao.impl;

import com.lord.dao.GenericMybatisDao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xj_xiaocheng on 2015/1/29.
 */
public class GenericMybatisDaoImpl<T, PK extends Serializable> implements GenericMybatisDao<T, PK> {

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
        return null;
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
