package com.lviv.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lviv.model.User;
import com.lviv.model.userdatas.UserAddress;
import com.lviv.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	Set<UserAddress> setAdress = new HashSet<UserAddress>() ;
	

	@RequestMapping(value="/addUser", method = RequestMethod.GET)
	public ModelAndView go() {
		ModelAndView mv = new ModelAndView("user"); 
		return mv;
    }
	
	
	
	@RequestMapping(value="/userreg", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute ("user") User user, @ModelAttribute ("userAddress") UserAddress userAddress) {
  	    	
  		ModelAndView mv = new ModelAndView("user");
//  		UserAddress useradr = new UserAddress("aaa", "sss", "ddd", "fff");
//  		UserAddress useradr1 = new UserAddress("zzzaaa", "xxxxsss", "ccccddd", "vvvvfff");
//  		useradr.setUser(user);
//  		useradr1.setUser(user);

  		
  		setAdress.add(userAddress);
//  		setAdress.add(useradr);
//  		setAdress.add(useradr1);  	
  		user.setUserAddress(setAdress);
  		userService.save(user);
  		mv.addObject("user", user);
  		mv.addObject("userAddress",userAddress );
  		
  		return mv;
    }
	
	@RequestMapping(value="/showuser", method = RequestMethod.POST)
    public ModelAndView showUser(@ModelAttribute ("user") User user) {
  	    	
  		ModelAndView mv = new ModelAndView("products");
  		
  		
  		return mv;
    }
}
