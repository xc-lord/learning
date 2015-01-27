package com.lord.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lord.dao.IGenericDao;

/**
 * 使用泛型实现的通用DAO接口Hibernate实现类
 * @author XiaoCheng
 * @Data   2013-7-22
 * @param <T>	对象
 * @param <PK>	主键
 */
public class GenericDaoImpl<T,PK extends Serializable> implements IGenericDao<T, PK>{

	private Class<T> clazz;
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		//获得超类的泛型参数的实际类型
		clazz = (Class<T>) ((ParameterizedType) super.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	
	/**
	 *  获取持久化对象的名称
	 */
	public String getClassName() {
		return clazz.getSimpleName();
	}

	/**
	 * 获得Hibernate的Session对象
	 */
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Long count(String hql, Object[] params) {
		if(params != null && params.length != StringUtils.countMatches(hql, "?")) {
			throw new RuntimeException("count() hql参数与传入参数params的个数不一致");
		}
		Long retVal = 0L;
		int index = 0;
		Query query = getCurrentSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (Object obj : params) {
				query.setParameter(index++, obj);
			}
		}
		retVal = (Long) query.iterate().next();

		return retVal;
	}
	
	@Override
	public Long countAll() {
		String hql = "select count(*) from " + getClassName();
        return count(hql, null); 
	}
	
	@Override
	public Long countBy(String propertyName, Object value) {
		// 判断传入参数是否为空
		if (StringUtils.isBlank(propertyName) || value == null)
			throw new RuntimeException("countBy()传入属性和其对应值为空");

		// 组合HQL语句
		String hql = "select count(*) from " + getClassName() + " where " + propertyName + "= ?";
		
		return count(hql, new Object[]{value});
	}

	@Override
	public Long countLikeBy(String propertyName, String value) {
		// 判断传入参数是否为空
		if (propertyName == null || "".equals(propertyName) || value == null)
			throw new RuntimeException("countBy()传入属性和其对应值为空");

		// 组合Hql语句
		String hql = "select count(*) from  " + getClassName() + 
				" where " + propertyName + "like '%" + value + "%' ";
		
		return count(hql, null);
	}

	@Override
	public int executeUpdate(String hql, Object[] params) {
		if(params != null && params.length != StringUtils.countMatches(hql, "?")) {
			throw new RuntimeException("executeUpdate() hql参数与传入参数params的个数不一致");
		}
		Query query = getCurrentSession().createQuery(hql);
		int index = 0;
		if (params != null && params.length > 0) {
			for (Object obj : params) {
				query.setParameter(index++, obj);
			}
		}
		return query.executeUpdate();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		return (T) getCurrentSession().get(clazz,id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T load(PK id) {
		return (T) getCurrentSession().load(clazz, id);
	}

	@Override
	public T getBy(String propertyName, Object value) {
		//先查出2个，以确定是否有2个以上的对象
		List<T> retVal = queryBy(propertyName, value, 0, 2);
		if (retVal != null && retVal.size() == 1) {
			return retVal.get(0);
		} else if (retVal != null && retVal.size() > 1) {
			throw new RuntimeException("getBy()获得的对象不只一个");
		} else
			return null;
	}

	@Override
	public List<T> qreryAll() {
        return query("from " + getClassName(), null);
	}
	
	@Override
	public List<T> query(String hql, Object[] params) {
		return query(hql, params, QUERY_ALL, QUERY_ALL);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> query(String hql, Object[] params, int start, int howMany) {
		if(params != null && params.length != StringUtils.countMatches(hql, "?")) {
			throw new RuntimeException("query() hql参数与传入参数params的个数不一致");
		}
		
		int index = 0;
		Query query = getCurrentSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (Object obj : params) {
				query.setParameter(index++, obj);
			}
		}

		if (start >= 0 && howMany > 0) {
			query.setFirstResult(start);
			query.setMaxResults(howMany);
		}
		List<T> retVal = query.list();
		if (retVal != null && retVal.size() >= 0)
			return retVal;
		else
			return null;
	}

	@Override
	public List<T> queryAll(int start, int howMany) {
        return query("from " + getClassName(), null, start, howMany);
	}

	@Override
	public List<T> queryBy(String propertyName, Object value) {
		return queryBy(propertyName, value, QUERY_ALL, QUERY_ALL);
	}

	@Override
	public List<T> queryBy(String propertyName, Object value, int start, int howMany) {
		// 判断传入参数是否为空
		if (propertyName == null || "".equals(propertyName) || value == null)
			throw new RuntimeException("queryBy()传入属性和其对应值为空");
		
		// 组合Hql语句
		String hql = "from " + getClassName() + " where " + propertyName + "= ?";
		
		return query(hql, new Object[]{value}, start, howMany);
	}

	@Override
	public List<T> queryLikeBy(String propertyName, String value) {
		return queryLikeBy(propertyName, value, QUERY_ALL, QUERY_ALL);
	}

	@Override
	public List<T> queryLikeBy(String propertyName, String value, int start,int howMany) {
		// 判断传入参数是否为空
		if (propertyName == null || "".equals(propertyName) || value == null)
			throw new RuntimeException("queryLikeBy()传入属性和其对应值为空");
		
		// 组合Hql语句
		String hql = "from " + getClassName() + " where " + propertyName + "like '%" + value + "%' ";
				
		return query(hql, null, start, howMany);
	}
	
	@Override
	public T queryOne(String hql, Object[] params) {
		List<T> list = query(hql, params);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void remove(PK... id) {
		for (PK pk : id) {
			getCurrentSession().delete(getCurrentSession().get(clazz, pk));
		}
	}

	@Override
	public void remove(String propertyName, Object propertyValue) {
		String queryString = "delete from " + getClassName()  
                + " as model where model." + propertyName + "= ?";  
        Query query = getCurrentSession().createQuery(queryString);  
        query.setParameter(0, propertyValue);
        query.executeUpdate();		
	}
	
	@Override
	public void remove(T entity) {
		getCurrentSession().delete(entity);
	}

	@Override
	public void save(T entity) {
		getCurrentSession().save(entity);
	}
	
	@Override
	public void update(T entity) {
		getCurrentSession().update(entity);
	}
	
}
