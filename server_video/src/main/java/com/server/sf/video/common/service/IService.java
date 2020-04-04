package com.server.sf.video.common.service;

import java.util.List;

public interface IService<T> {

	public T find(Class<T> clazz, String id) throws Exception;

//	@Transactional(propagation = Propagation.REQUIRED)
	public void save(T baseBean)throws Exception;
//	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T BaseBean)throws Exception;
//	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(T baseBean)throws Exception;

	public List<T> list(String hql)throws Exception;

	public int getTotalCount(String hql, Object... params)throws Exception;

	public List<T> list(String hql, int firstResult, int maxSize,
                        Object... params)throws Exception;
	
}
