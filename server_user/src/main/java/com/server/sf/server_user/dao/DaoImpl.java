package com.server.sf.server_user.dao;


import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;



@Configuration
@Repository
public class DaoImpl<T>  implements IDao<T> {


	@Autowired
	private EntityManagerFactory entityManagerFactory;


	private SessionFactory sessionFactory;
	
	public void create(T baseBean) throws Exception {
		try {
//			this.entityManagerFactory.sa

			getSession().save(baseBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void clearCaChe() throws Exception{
		try {
			getSession().clear();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public Query createQuery(String hql) throws Exception {
		Query q = null;
		try {
			q = getSession().createQuery(hql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return q;
	}

	@Override
	public SQLQuery createSqlQuery(String sql) throws Exception {
		SQLQuery q = null;
		try {
			q = getSession().createSQLQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return q;
	}

	public void delete(T baseBean) throws Exception {
		try {
			getSession().delete(baseBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}



	public int getTotalCount(String hql, Object... params) throws Exception {
		int j = 0;
		try {
			Query query = getSession().createQuery(hql);
			for (int i = 0; params != null && i < params.length; i++)
				query.setParameter(i + 1, params[i]);
			Object obj = createQuery(hql).uniqueResult();
			j = ((Long) obj).intValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return j;
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String hql) throws Exception {
		List<T> list = null;
		try {
			Query query = getSession().createQuery(hql);
			list = (List<T>) query.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String hql, int firstResult, int maxResults,
			Object... params) throws Exception {
		List<T> list = null;
		try {
			Query query = getSession().createQuery(hql);
			for (int i = 0; params != null && i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
			list = createQuery(hql).setFirstResult(firstResult)
					.setMaxResults(maxResults).list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	public T save(T baseBean)  throws Exception{
		try {
			getSession().save(baseBean);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			throw e;
		}finally {
//			sessionFactory.getCurrentSession().close();
		}
		return baseBean;
	}
	
	public T update(T baseBean)  throws Exception{
		try {
//			sessionFactory.openSession().update(sessionFactory.openSession().merge(baseBean));
			getSession().merge(baseBean);
			getSession().clear();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
//			sessionFactory.getCurrentSession().close();
			e.printStackTrace();
			throw e;
		}
		finally {
//			sessionFactory.getCurrentSession().close();
		}
		return baseBean;
	}

	@Override
	public T get(Class<T> clazz, String id) throws Exception {
		// TODO Auto-generated method stub
		T t = null;
		try {
			t = (T) getSession().get(clazz, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		return t;
	}


	public SessionFactory getSessionFactory() {
		if (sessionFactory == null){
//			sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
			sessionFactory = getSession().getSessionFactory();
		}
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@PersistenceContext
	protected EntityManager entityManager;

	protected Session getSession() {
		return entityManager.unwrap(Session.class);
	}

}
