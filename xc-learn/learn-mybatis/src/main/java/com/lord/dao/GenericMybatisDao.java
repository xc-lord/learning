package com.lord.dao;

import java.io.Serializable;
import java.util.List;

/**
 * GenericDao DAO层泛型接口，定义基本的DAO功能
 *
 * @param <T>  实体类
 * @param <PK> 主键类，必须实现Serializable接口
 * @author xiaocheng
 * @since 2015/1/28
 */
public interface GenericMybatisDao<T, PK extends Serializable> {
    /**
     * 插入一个实体（在数据库INSERT一条记录）
     *
     * @param entity 实体对象
     */
    public void insert(T entity);

    /**
     * 修改一个实体对象（UPDATE一条记录）
     *
     * @param entity 实体对象
     * @return 修改的对象个数，正常情况=1
     */
    public int update(T entity);

    /**
     * 按主键删除记录
     *
     * @param primaryKey 主键对象
     * @return 删除的对象个数，正常情况=1
     */
    public int delete(PK... primaryKey);

    /**
     * 查询整表总记录数
     *
     * @return 整表总记录数
     */
    public int count();

    /**
     * 按主键取记录
     *
     * @param primaryKey 主键值
     * @return 记录实体对象，如果没有符合主键条件的记录，则返回null
     */
    public T get(PK primaryKey);

    /**
     * 取全部记录
     *
     * @return 全部记录实体对象的List
     */
    public List<T> select();

    /**
     * 批量插入
     *
     * @param list
     */
    public void batchInsert(final List<T> list);

    /**
     * 批量修改
     *
     * @param list
     */
    public void batchUpdate(final List<T> list);

    /**
     * 批量删除
     *
     * @param list
     */
    public int batchDelete(final List<PK> list);

    /**
     * 根据值相等查询
     * @param entity
     * @return  对象列表
     */
    public List<T> query(T entity);

    /**
     * 根据值相等查询
     * @param entity
     * @return  一个对象
     */
    public T queryOne(T entity);
}