package com.lviv.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lviv.dao.inter.ArticleDAO;
import com.lviv.model.Article;
import com.lviv.model.ItemTree;

@Repository
public class ArticleDAOimpl implements ArticleDAO {
	
	@Autowired
	SessionFactory sessionFactory;


	@Override
	public void saveArticle(Article article) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(article);
		tx.commit();
		session.close();
	}

	@Override
	public List<Article> getAllArticles() {

		List<Article> articleList = null;

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM Article";
		Query query = session.createQuery(hql);
		articleList = query.list();
		//articleList = session.createCriteria(Article.class).list();
		session.close();
		return articleList;
	}

	@Override
	public Article getArticleById(Integer id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Article article = (Article) session.get(Article.class, id);
		session.close();
		return article;
	}

	@Override
	public void updateArticle(Article article) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(article);
		tx.commit();
		session.close();
		
	}

	@Override
	public void deleteArticle(Article article) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "DELETE FROM `istore`.`article` WHERE `idArticle`= :id";
		Query query = session.createSQLQuery(sql).setParameter("id", article.getIdArticle().toString());
		query.executeUpdate();
		tx.commit();
		session.close();
		
	}
	
	@Override
	public List<Article> getListByCategorys(List<ItemTree> itemTreeList) {
		List<Article> list = null;
		List<Article> productList = new ArrayList<Article>();
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		for (ItemTree itemTree : itemTreeList) {
			/*String sql = "SELECT FROM istore_adv1.article WHERE idItemTree= :id";
			Query query = session.createSQLQuery(sql).setParameter("id", itemTree.getIdItemTree().toString());
			list = query.list();
			*/
			
			
			String hql = "FROM Article A WHERE A.itemTree.idItemTree = :id_ItemTree";
			Query query = session.createQuery(hql);
			query.setParameter("id_ItemTree", itemTree.getIdItemTree());
			list = query.list();
			productList.addAll(list);
		}
		
		session.close();
		return productList;
		
	}

}
