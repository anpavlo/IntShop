package com.lviv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.dao.inter.ArticleParamDAO;
import com.lviv.model.ArticleParam;

@Service
public class ArticleParamService {
	
	@Autowired
	ArticleParamDAO articleParamDAO;
	
	public void save(ArticleParam paramValue){
		articleParamDAO.save(paramValue);
	}	
	
	public void update(ArticleParam paramValue){
		articleParamDAO.update(paramValue);
	}
	
	public void delete(ArticleParam paramValue){
		articleParamDAO.delete(paramValue);
	}
	public ArticleParam getById(Integer id){
		return articleParamDAO.getById(id);
	}
	

}
