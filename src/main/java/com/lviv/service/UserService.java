package com.lviv.service;

import com.lviv.dao.inter.UserDAO;
import com.lviv.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    UserDAO userDAO;
    
    public User getById(Integer id){
       return userDAO.getById(id);
    }
    
    public void save (User user){
    	userDAO.save(user);
    }
}
