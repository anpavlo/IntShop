package com.lviv.dao.inter;

import java.util.List;

import com.lviv.model.ParamValue;

public interface ParamValueDAO {
	
	public void save(ParamValue paramValue);
	public List<ParamValue> getAll();
	public ParamValue getById(Integer id);
	public void update(ParamValue paramValue);
	public void delete(ParamValue paramValue);
	public ParamValue getByValueAndUnits(String value, String units);

}
