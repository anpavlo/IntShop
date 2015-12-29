package com.lviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lviv.model.ItemTree;
import com.lviv.service.ItemTreeService;

@Controller
public class MainController {

	@Autowired
	ItemTreeService itemTreeService;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView go() {
		ModelAndView mv = new ModelAndView("main");
		ItemTree itemTreeMenu = itemTreeService.getById(itemTreeService.getRootId());
		mv.addObject("itemTree", itemTreeMenu);
		return mv;
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView("main");
		ItemTree itemTreeMenu = itemTreeService.getById(itemTreeService.getRootId());
		mv.addObject("itemTree", itemTreeMenu);
		return mv;
	}

	@RequestMapping(value = "/partners", method = RequestMethod.GET)
	public ModelAndView partners() {
		ModelAndView mv = new ModelAndView("partners");
		ItemTree itemTreeMenu = itemTreeService.getById(itemTreeService.getRootId());
		mv.addObject("itemTree", itemTreeMenu);
		return mv;
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("aboutus");
		ItemTree itemTreeMenu = itemTreeService.getById(itemTreeService.getRootId());
		mv.addObject("itemTree", itemTreeMenu);
		return mv;
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("contacts");
		ItemTree itemTreeMenu = itemTreeService.getById(itemTreeService.getRootId());
		mv.addObject("itemTree", itemTreeMenu);
		return mv;
	}
}
