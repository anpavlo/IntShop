package com.lviv.utility;

import java.io.Serializable;
import java.util.List;

import com.lviv.model.ItemTree;

public class ArticleData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5991997260179010292L;
	private Double price;
	private Integer quantity;
	private String description;
	private List<ParamsData>  ParamsDataList;
	private ItemTree itemTree;
	
	public ArticleData() {
		super();
	}
	

	public ArticleData(Double price, Integer quantity, String description, List<ParamsData> paramsDataList) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		ParamsDataList = paramsDataList;
	}


	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ParamsData> getParamsDataList() {
		return ParamsDataList;
	}
	public void setParamsDataList(List<ParamsData> paramsDataList) {
		ParamsDataList = paramsDataList;
	}


	public ItemTree getItemTree() {
		return itemTree;
	}


	public void setItemTree(ItemTree itemTree) {
		this.itemTree = itemTree;
	}
	
	
	
	

}
