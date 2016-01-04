package com.lviv.dao.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lviv.dao.inter.ItemTreeDAO;
import com.lviv.model.ItemTree;

@Repository
public class ItemTreeDAOimpl implements ItemTreeDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(ItemTree itemTree) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(itemTree);
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
	public List<ItemTree> getAll() {
		Session session = null;
		try {
			List<ItemTree> itemTreeList = null;
			session = sessionFactory.openSession();
			session.beginTransaction();
			itemTreeList = session.createCriteria(ItemTree.class).list();
			return itemTreeList;
		} catch (HibernateException e) {
			throw (e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public ItemTree getById(Integer id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			ItemTree itemTree = (ItemTree) session.get(ItemTree.class, id);
			if (itemTree != null) {
				fillChildren(itemTree);
				fillParentParams(itemTree);
			}
			return itemTree;
		} catch (HibernateException e) {
			throw (e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public ItemTree getByIdSingleObject(Integer id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			ItemTree itemTree = (ItemTree) session.get(ItemTree.class, id);
			return itemTree;
		} catch (HibernateException e) {
			throw (e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	private ItemTree fillParentParams(ItemTree itemTree) {
		ItemTree parent, child;
		child = itemTree;
		while (child.getParentId() != 0) {
			parent = getByIdSingleObject(child.getParentId());
			itemTree.getParentParams().addAll(parent.getParams());
			child = parent;
		}
		return itemTree;
	}

	private TreeSet<ItemTree> getChildren(ItemTree itemTree) {

		TreeSet<ItemTree> children = null;
		List<ItemTree> list = null;
		int id = itemTree.getIdItemTree();

		Session session = null;
		try {
			session = sessionFactory.openSession();
			String hql = "FROM ItemTree I WHERE I.parentId = :id_ItemTree";
			Query query = session.createQuery(hql);
			query.setParameter("id_ItemTree", id);
			list = query.list();
			children = new TreeSet<ItemTree>(list);
			return children;
		} catch (HibernateException e) {
			throw (e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public void update(ItemTree itemTree) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(itemTree);
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
	public void delete(ItemTree itemTree) {

		deleteWithChildren(itemTree);

	}

	private ItemTree fillChildren(ItemTree itemTree) {

		TreeSet<ItemTree> children = getChildren(itemTree);

		if (children.size() != 0) {
			itemTree.setChildrenList(children);
			for (ItemTree child : children) {
				fillChildren(child);
			}

		}

		return itemTree;
	}

	private ItemTree deleteWithChildren(ItemTree itemTree) {

		Set<ItemTree> children = getChildren(itemTree);

		if (children.size() != 0) {

			for (ItemTree child : children) {
				deleteWithChildren(child);
			}
		}

		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			session.delete(itemTree);

			tx.commit();
			return itemTree;
		} catch (HibernateException e) {
			throw (e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public Integer getRootId() {
		List<ItemTree> list = null;
		ItemTree itemTree = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			String hql = "FROM ItemTree I WHERE I.parentId = :id_ItemTree";
			Query query = session.createQuery(hql);
			query.setParameter("id_ItemTree", 0);// parentId = 0 mean that it is
													// the
													// root
			list = query.list();

			if (list.isEmpty()) {
				itemTree = new ItemTree("Products", 0);
				session.save(itemTree);
				tx.commit();
				query = session.createQuery(hql);
				query.setParameter("id_ItemTree", 0);
				list = query.list();
				itemTree = list.get(0);

			} else {
				itemTree = list.get(0);
			}
			return itemTree.getIdItemTree();
		} catch (HibernateException e) {
			throw (e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

}
