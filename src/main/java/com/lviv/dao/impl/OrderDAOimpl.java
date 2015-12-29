package com.lviv.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.lviv.dao.inter.OrderDAO;
import com.lviv.model.Article;
import com.lviv.model.Order;
import com.lviv.model.User;

public class OrderDAOimpl implements OrderDAO {
	
	@Autowired
	SessionFactory sessionFactory;


	@Override
	public void save(Order order) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(order);
		tx.commit();
		session.close();
	}

	@Override
	public List<Order> getAll() {
		List<Order> orderList = null;

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		orderList = session.createCriteria(Order.class).list();
		session.close();
		return orderList;
		
	}

	@Override
	public Order getById(Integer id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Order order = (Order) session.get(Order.class, id);
		session.close();
		return order;
	}

	@Override
	public void update(Order order) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(order);
		tx.commit();
		session.close();
		
	}

	@Override
	public void delete(Order order) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = "DELETE FROM `istore`.`order` WHERE `idOrder`= :id";
		Query query = session.createSQLQuery(sql).setParameter("id", order.getIdOrder().toString());
		query.executeUpdate();
		tx.commit();
		session.close();
		
	}
	@Override
	public Order getOpenedOrder(User user) {
		Order order = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM Order O WHERE O.opendate is not null and O.closedate is null and O.user.idUser = :id_User";
		Query query = session.createQuery(hql);
		query.setParameter("id_User", user.getIdUser());
		List results = query.list();
		if (results.size()>0){
			order = (Order) results.get(0);
			}
		session.close();
		return order;
	}

}
