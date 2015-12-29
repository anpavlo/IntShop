package com.lviv.dao.inter;

import java.util.List;

import com.lviv.model.Order;
import com.lviv.model.User;



public interface OrderDAO {
	public void save(Order order);
	public List<Order> getAll();
	public Order getById(Integer id);
	public void update(Order order);
	public void delete(Order order);
	public Order getOpenedOrder(User user);
}
