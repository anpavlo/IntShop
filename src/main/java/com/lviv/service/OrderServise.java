package com.lviv.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.lviv.dao.inter.ArticleDAO;
import com.lviv.dao.inter.OrderDAO;
import com.lviv.dao.inter.UserDAO;
import com.lviv.model.Article;
import com.lviv.model.ArticleOrder;
import com.lviv.model.Order;
import com.lviv.model.User;

/* 
 * Opened order ((order.opendate!=null)@@(order.closedate==null)) means that user add at least one Article to the Basket
 * Closed order((order.opendate!=null)@@(order.closedate!=null)) means that user press button "BUY" 
 * 
 */

public class OrderServise {

	@Autowired
	UserDAO userDAO;

	@Autowired
	ArticleDAO articleDAO;

	@Autowired
	OrderDAO orderDAO;

	public void addToBasket(User user, Article article, Date date, Integer quantity) {
		Order order;
		Set<ArticleOrder> articleOrders;
		
		order = getOpenedOrder(user);
		
		if (order==null) {
			openOrder(user, date);
			order = getOpenedOrder(user);
		}

		ArticleOrder articleOrder = new ArticleOrder();
	

		if (article.getArticleOrders() == null) {
			articleOrders = new HashSet<ArticleOrder>();
		} else {
			articleOrders = article.getArticleOrders();
		}

		articleOrder.setArticle(article);
		articleOrder.setOrder(order);
		articleOrder.setQuantity(quantity);		
		
		articleOrders.add(articleOrder);

		article.setArticleOrders(articleOrders);

		order.setArticleOrders(articleOrders);

		orderDAO.update(order);
		articleDAO.updateArticle(article);

	}
	
//	if OpenedOrder not found return null
	private Order getOpenedOrder(User user) {
		return orderDAO.getOpenedOrder(user);
	}

	private void openOrder(User user, Date date) {
		Order order = new Order();
		order.setUser(user);
		order.setOpenDate(date);
		orderDAO.save(order);
	}

}
