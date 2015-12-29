package com.lviv.dao.inter;

import java.sql.SQLException;
import java.util.List;

import com.lviv.model.User;



public interface UserDAO {
	public void save(User user);
	public List<User> getAll();
	public User getById(Integer id);
	public void update(User user);
	public void delete(User user);
	public User getByLogin(String userLogin);

}
