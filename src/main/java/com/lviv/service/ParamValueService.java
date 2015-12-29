package com.lviv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.dao.inter.ParamValueDAO;
import com.lviv.model.ParamValue;

@Service
public class ParamValueService {
	
	@Autowired
	ParamValueDAO paramValueDAO;
	
	public void save(ParamValue paramValue){
		paramValueDAO.save(paramValue);
	}	
	
	public void update(ParamValue paramValue){
		paramValueDAO.update(paramValue);
	}
	
	public void delete(ParamValue paramValue){
		paramValueDAO.delete(paramValue);
	}
	public ParamValue getById(Integer id){
		return paramValueDAO.getById(id);
	}
	
	public ParamValue getByValueAndUnits(String value, String units){
		return paramValueDAO.getByValueAndUnits(value,units);
	}

}
