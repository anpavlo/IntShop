package com.lviv.dao.inter;

import java.util.List;

import com.lviv.model.ItemTree;

public interface ItemTreeDAO {
	public void save(ItemTree itemTree);
	public List<ItemTree> getAll();
	public ItemTree getById(Integer id);
	public void update(ItemTree itemTree);
	public void delete(ItemTree itemTree);
	public Integer getRootId();
	public ItemTree getByIdSingleObject(Integer id);
	



}
