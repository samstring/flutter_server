package com.server.sf.server_user.dao;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import java.util.List;


public interface IDao<T> {

	public T get(Class<T> clazz, String id) throws Exception;

	public void create(T baseBean)throws Exception;

	public T save(T baseBean)throws Exception;
	
	public T update(T baseBean)  throws Exception;

	public void delete(T baseBean)throws Exception;
	
	public List<T> list(String hql)throws Exception;

	public int getTotalCount(String hql, Object... params)throws Exception;

	public List<T> list(String hql, int firstResult, int maxSize,
                        Object... params)throws Exception;

	public Query createQuery(String hql)throws Exception;

	public SQLQuery createSqlQuery(String sql) throws  Exception;

	public void clearCaChe() throws Exception;
	
}
