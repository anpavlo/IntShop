package com.lviv.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lviv.dao.inter.ParamDAO;
import com.lviv.model.Param;

@Repository
public class ParamDAOimpl implements ParamDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(Param param) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(param);
		tx.commit();
		session.close();
	}

	@Override
	public List<Param> getAll() {

		List<Param> paramList = null;

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		paramList = session.createCriteria(Param.class).list();
		session.close();
		return paramList;
	}

	@Override
	public Param getById(Integer id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Param param = (Param) session.get(Param.class, id);
		session.close();
		return param;
	}

	@Override
	public void update(Param param) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(param);
		tx.commit();
		session.close();

	}

	@Override
	public void delete(Param param) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(param);
			tx.commit();
		} catch (HibernateException e) {
			throw (e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public Param getByValue(String value) {
		Session session = null;
		Param param = null;
		try {

			session = sessionFactory.openSession();
			String hql = "FROM Param P WHERE P.paramName = :param_Name";
			Query query = session.createQuery(hql);
			query.setParameter("param_Name", value);
			List<Param> params = query.list();
			if (!params.isEmpty()) {
				param = (Param) params.get(0);
			}
			return param;

		} catch (HibernateException e) {
			throw (e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public Param getByPriorityAndIdItemTree(Integer priority, Integer IdItemTree) {
		Param param = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String hql = "FROM Param P WHERE P.itemTree.idItemTree  = :id_ItemTree and P.priority = :prior";
			Query query = session.createQuery(hql);
			query.setParameter("id_ItemTree", IdItemTree);
			query.setParameter("prior", priority);
			List<Param> params = query.list();
			if (!params.isEmpty()) {
				param = (Param) params.get(0);
			}
			return param;

		} catch (HibernateException e) {
			throw (e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

}
