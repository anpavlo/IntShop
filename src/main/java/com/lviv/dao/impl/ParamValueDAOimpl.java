package com.lviv.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lviv.dao.inter.ParamValueDAO;
import com.lviv.model.Param;
import com.lviv.model.ParamValue;

@Repository
public class ParamValueDAOimpl implements ParamValueDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(ParamValue paramValue) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(paramValue);
		tx.commit();
		session.close();
		
	}

	@Override
	public List<ParamValue> getAll() {
		List<ParamValue> paramValueList = null;

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		paramValueList = session.createCriteria(ParamValue.class).list();
		session.close();
		return paramValueList;
	}

	@Override
	public ParamValue getById(Integer id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		ParamValue paramValue = (ParamValue) session.get(ParamValue.class, id);
		session.close();
		return paramValue;
	}

	@Override
	public void update(ParamValue paramValue) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(paramValue);
		tx.commit();
		session.close();
		
	}

	@Override
	public void delete(ParamValue paramValue) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(paramValue);
		tx.commit();
		session.close();
		
	}

	@Override
	public ParamValue getByValueAndUnits(String value, String units) {
		ParamValue paramValue = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM ParamValue P WHERE P.paramValue = :p_Value";
		Query query = session.createQuery(hql);
		query.setParameter("p_Value", value);
		List paramValues = query.list();
		session.close();
		if(!paramValues.isEmpty()){
			paramValue = (ParamValue) paramValues.get(0);
		}
		

		
		return paramValue;
	}

}
