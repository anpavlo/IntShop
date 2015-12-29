package com.lviv.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lviv.dao.inter.ArticleParamDAO;
import com.lviv.model.ArticleParam;


@Repository
public class ArticleParamDAOimpl implements ArticleParamDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(ArticleParam articleParam) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(articleParam);
		tx.commit();
		session.close();
		
	}

	@Override
	public List<ArticleParam> getAll() {
		List<ArticleParam> articleParamList = null;

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		articleParamList = session.createCriteria(ArticleParam.class).list();
		session.close();
		return articleParamList;
	}

	@Override
	public ArticleParam getById(Integer id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		ArticleParam articleParam = (ArticleParam) session.get(ArticleParam.class, id);
		session.close();
		return articleParam;
	}

	@Override
	public void update(ArticleParam articleParam) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(articleParam);
		tx.commit();
		session.close();
		
	}

	@Override
	public void delete(ArticleParam articleParam) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(articleParam);
		tx.commit();
		session.close();
		
	}

}
