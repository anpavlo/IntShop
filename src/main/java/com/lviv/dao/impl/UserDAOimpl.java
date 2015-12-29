package com.lviv.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lviv.dao.inter.UserDAO;
import com.lviv.model.User;
import com.lviv.model.userdatas.UserAddress;


@Repository
public class UserDAOimpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Set<UserAddress> addresses = user.getUserAddress();
		for(UserAddress adr: addresses){
			adr.setUser(user);
		}
		
		session.save(user);
		tx.commit();
		session.close();
	}

	@Override
	public List<User> getAll() {

		List<User> userList = null;

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		userList = session.createCriteria(User.class).list();
		session.close();
		return userList;
	}

	@Override
	public User getById(Integer id) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User user = (User) session.get(User.class, id);
		session.close();
		return user;

	}
	
	@Override
	public User getByLogin(String userLogin) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM User U WHERE U.userLogin = :user_Login";
		Query query = session.createQuery(hql);
		query.setParameter("user_Login", userLogin);
		List results = query.list();
		User user = (User) results.get(0);
		session.close();
		return user;
	}

	@Override //user must have at least login 
	public void update(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		if (user.getIdUser() == null) {
			User user1 = getByLogin(user.getUserLogin());
			user.setIdUser(user1.getIdUser());
		}

		session.update(user);
		tx.commit();
		session.close();
	}

	@Override //user must have at least login 
	public void delete(User user) {
		User user1 = null;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		if (user.getIdUser() == null) {
			user1 = getByLogin(user.getUserLogin());
			user.setIdUser(user1.getIdUser());
		}

		String sql = "DELETE FROM `istore`.`user` WHERE `idUser`= :id";
		Query query = session.createSQLQuery(sql).setParameter("id", user1.getIdUser().toString());
		query.executeUpdate();
		tx.commit();
		session.close();

	}

}
