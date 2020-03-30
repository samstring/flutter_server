package com.server.sf.server_user.base.service;

import com.server.sf.server_user.IDao;
import com.server.sf.server_user.user.model.BaseBean;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Data
public abstract class ServiceImpl<T extends BaseBean> implements IService<T> {

	@Autowired
	private IDao<T> dao;

	public IDao<T> getDao() {
		return dao;
	}

	public void setDao(IDao<T> dao) {
		this.dao = dao;
	}

	public T find(Class<T> clazz, String id) throws Exception {
		T t = null;
		try {
			t = dao.get(clazz, id);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

		return t;
	}


	public void delete(T baseBean) throws Exception {

		try {
			dao.delete(baseBean);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

	public int getTotalCount(String hql, Object... params) throws Exception {

		int i = 0;

		try {
			i = dao.getTotalCount(hql, params);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

		return i;
	}

	public void save(T baseBean) throws Exception {
		try {
			dao.save(baseBean);
		} catch (Exception e) {
			// TODO: handle exception\
			throw e;
		}

	}
	
	@Override
	public void update(T baseBean) throws Exception {
		// TODO Auto-generated method stub
		try {
			dao.update(baseBean);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public List<T> list(String hql) throws Exception {
		List<T> list = null;

		try {
			list = dao.list(hql);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

		return list;
	}

	public List<T> list(String hql, int firstResult, int maxSize,
			Object... params) throws Exception {

		List<T> list = null;

		try {
			list = dao.list(hql, firstResult, maxSize, params);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

		return list;
	}
	
	

}
