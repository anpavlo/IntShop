package com.lviv.dao.inter;

import java.util.List;

import com.lviv.model.ArticleOrder;



public interface ArticleOrderDAO {
	
	public void saveArticleOrder(ArticleOrder articleOrder);
	public List<ArticleOrder> getAllArticleOrders();
	public ArticleOrder getArticleOrderById(Integer id);
	public void updateArticleOrder(ArticleOrder articleOrder);
	public void deleteArticleOrder(ArticleOrder articleOrder);
}
