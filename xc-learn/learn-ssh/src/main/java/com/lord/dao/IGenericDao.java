package com.lord.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 使用泛型实现的通用DAO接口
 * @author XiaoCheng
 * @Data   2013-7-22
 * @param <T>	对象
 * @param <PK>	主键
 */
public interface IGenericDao<T, PK extends Serializable> {
	final int QUERY_ALL = -1;

	/**
	 * 根据一个查询条件及其参数，所有对象的个数
	 * @param hql		完整的查询语句，使用位置参数。比如：select user from User
	 * @param params	查询条件中的参数的值。使用Object数组，要求顺序和查询条件中的参数位置一致。
	 * @return	所有对象的个数。
	 */
	Long count(String hql, Object[] params);
	
	/**
	 * 根据对象的一个属性名和该属性名对应的值获得所有对象的个数。
	 * @param propertyName	属性名
	 * @param value			属性名对应的值
	 * @return	返回对象的个数
	 */
	Long countBy(String propertyName, Object value);
	
	/**
	 * 获得所有对象的个数
	 */
	Long countAll();
	
	/**
	 * 根据对象的一个属性名和该属性名对应的值获得like查询所有对象的个数。
	 * @param propertyName	属性名
	 * @param value			属性名对应的值
	 * @return	返回对象的个数
	 */
	Long countLikeBy(String propertyName, String value);
	
	/**
	 * 执行一条更新语句,如删除,修改
	 * @param hql		HQL语句
	 * @param params	参数
	 * @return			影响行数
	 */
	int executeUpdate(String hql, Object[] params);
	
	/**
	 * 根据对象id获得一个对象
	 * @param id	对象id
	 * @return		对象
	 */
	T get(PK id);
	
	/**
	 * 根据对象id，加载一个对象
	 * @param id	对象id
	 * @return		对象
	 */
	T load(PK id);

	/**
	 * 根据对象的一个属性名和该属性名对应的值来查找一个对象。
	 * @param propertyName	属性名
	 * @param value			属性名对应的值
	 * @return 一个对象，如果在该属性名和值的条件下找到多个对象，返回第一个对象,并打印日志
	 */
	T getBy(String propertyName, Object value);
	
	/**
	 * 列出所有的对象
	 * @return
	 */
	List<T> qreryAll();
	
	/**
	 * 根据一个查询条件及其参数，查找任意类型的对象。
	 * @param hql		完整的查询语句，使用位置参数。比如：select user from User
	 * @param params	查询条件中的参数的值。使用Object数组，要求顺序和查询条件中的参数位置一致。
	 * @return	一个任意对象的List对象，如果没有查到任何数据，返回一个空的List对象。
	 */
	List<T> query(String hql, Object[] params);
	
	/**
	 * 根据一个查询条件及其参数，还有开始查找的位置和查找的个数来查找任意类型的对象。
	 * @param hql		完整的查询语句，使用位置参数。比如：select user from User
	 * @param params	查询条件中的参数的值。使用Object数组，要求顺序和查询条件中的参数位置一致。
	 * @param start		开始查询的位置
	 * @param howMany	需要查询的对象的个数，-1则为全部
	 * @return	一个任意对象的List对象，如果没有查到任何数据，返回一个空的List对象。
	 */
	List<T> query(String hql, Object[] params, int start, int howMany);
	
	/**
	 * 分页查找,所有对象
	 * @param start		开始
	 * @param howMany	结束
	 * @return
	 */
	List<T> queryAll(final int start, final int howMany);
	
	/**
	 * 根据对象的一个属性名和该属性名对应的值来查找所有对象
	 * @param propertyName	属性名
	 * @param value			属性名对应的值
	 * @return	返回多个对象的List集合
	 */
	List<T> queryBy(String propertyName, Object value);
	
	/**
	 * 根据对象的一个属性名和该属性名对应的值来查找所有对象
	 * @param propertyName	属性名
	 * @param value			属性名对应的值
	 * @param start			开始查询的位置
	 * @param howMany		需要查询的对象的个数，-1则为全部
	 * @return	返回多个对象的List集合
	 */
	List<T> queryBy(String propertyName, Object value, final int start, final int howMany);
	
	/**
	 * 根据对象的一个属性名和该属性名对应的值like查询所有对象。
	 * @param propertyName	属性名
	 * @param value			属性名对应的值
	 * @return	返回多个对象的List集合
	 */
	List<T> queryLikeBy(String propertyName, String value);
	
	/**
	 * 根据对象的一个属性名和该属性名对应的值like查询所有对象。
	 * @param propertyName	属性名
	 * @param value			属性名对应的值
	 * @param start			开始查询的位置
	 * @param howMany		需要查询的对象的个数，-1则为全部
	 * @return	返回多个对象的List集合
	 */
	List<T> queryLikeBy(String propertyName, String value, final int start, final int howMany);
	
	/**
	 * 根据一个查询条件及其参数，任意类型的一个对象。
	 * @param hql		完整的查询语句，使用位置参数。比如：select user from User
	 * @param params	查询条件中的参数的值。使用Object数组，要求顺序和查询条件中的参数位置一致。
	 * @return	一个任意对象的对象，如果没有查到任何数据，返回null。
	 */
	T queryOne(String hql, Object[] params);
	
	/**
	 * 根据ID,删除对象
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	void remove(PK... id);

	/** 
     * 通过属性删除 
     */  
    void remove(String propertyName, Object propertyValue);
	
	/**
	 * 删除对象
	 * @param entity
	 */
	void remove(T entity);
	
	/**
	 * 持久化一个对象,(保存对象)
	 * @param entity
	 * @return
	 */
	void save(T entity);
	
	/**
	 * 更新对象
	 * @param entity
	 */
	void update(T entity);
	
}
