package com.lviv.dao.inter;

import java.util.List;

import com.lviv.model.Param;

public interface ParamDAO {
	public void save(Param param);
	public List<Param> getAll();
	public Param getById(Integer id);
	public void update(Param param);
	public void delete(Param param);
	public Param getByValue(String value);
	
}
