package com.lviv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.dao.inter.ParamDAO;
import com.lviv.model.Param;

@Service
public class ParamService {
	
	@Autowired
	ParamDAO paramDAO;
	
	public void saveParam(Param param){
		paramDAO.save(param);
	}	
	
	public void updateParam(Param param){
		paramDAO.update(param);
	}
	
	public void deleteParam(Param param){
		paramDAO.delete(param);
	}
	public Param getById(Integer id){
		return paramDAO.getById(id);
	}
	
	public Param getByValue(String value){
		return paramDAO.getByValue(value);
	}

}
