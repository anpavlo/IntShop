package com.lviv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.dao.inter.ItemTreeDAO;
import com.lviv.model.ItemTree;

@Service
public class ItemTreeService {
	
	@Autowired
	ItemTreeDAO itemTreeDAO;
	
	public void addItem(ItemTree itemTree, int parentId){
		itemTree.setParentId(parentId);
		itemTreeDAO.save(itemTree);		
	}
	
	public void addItem(ItemTree itemTree){
		itemTreeDAO.save(itemTree);		
	}
	
	public ItemTree getById(int id){
		return itemTreeDAO.getById(id);
	}
	
	public ItemTree getByIdSingleObject(int id){
		return itemTreeDAO.getByIdSingleObject(id);
	}
	
	public List<ItemTree> getAllCategoriesOfRootParent(){
		//List<ItemTree> itemTreeList = itemTreeDAO.getAll();	
		List<ItemTree> itemTreeList = new ArrayList<ItemTree>();
		OUTER:
		for(ItemTree itemTree :itemTreeDAO.getAll()){
			for(ItemTree itemTree1 :itemTreeDAO.getAll()){
				if(itemTree.getIdItemTree()==itemTree1.getParentId()){
					continue OUTER;
				}
			}
			itemTreeList.add(itemTree);
		}
		
		return itemTreeList;
	}
	
	public List<ItemTree> getAllChildrenCategoriesByParentsId(Integer id){
		ItemTree itemTree = itemTreeDAO.getById(id);
		return getAllChildrenCategoriesByParentsId1(itemTree);
	}
	
	private List<ItemTree> getAllChildrenCategoriesByParentsId1(ItemTree itemTree){
		List<ItemTree> itemTreeList = new ArrayList<ItemTree>();
		TreeSet<ItemTree> children = itemTree.getChildrenList();
		
		if (children!=null) {
			for (ItemTree child : children) {
				itemTreeList.addAll(getAllChildrenCategoriesByParentsId1(child));
			}
		}else {
			itemTreeList.add(itemTree);
		}
		
		return itemTreeList;
	}
	
	public void deleteItem(ItemTree itemTree){
		itemTreeDAO.delete(itemTree);
	}
	public void updateItem(ItemTree itemTree){
		itemTreeDAO.update(itemTree);
	}
	
	public Integer getRootId(){
		return itemTreeDAO.getRootId();
	}
	

}
