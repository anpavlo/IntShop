package com.lviv.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name="ItemTree")
public class ItemTree implements Serializable, Comparable<ItemTree> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8752862812577424325L;

	@Id
	@Column(name = "idItemTree")
	@GeneratedValue
	private Integer idItemTree;
	
	@Column(name = "Itemvalue")
	private String value;
	
	@Column(name = "parentId", nullable=false)
	private Integer parentId;
	
	
	@OneToMany(mappedBy="itemTree", fetch=FetchType.EAGER)
	@javax.persistence.OrderBy("priority")
	@JsonManagedReference
	private SortedSet<Param> params; 
	
	
	@OneToMany(mappedBy="itemTree", fetch=FetchType. EAGER)
	@JsonManagedReference
	private Set<Article> articles;
	

	@Transient
	private TreeSet<ItemTree> childrenList;
	
	@Transient
	private SortedSet<Param> parentParams = new TreeSet<Param>();
	

	public ItemTree() {
		super();
	}
	
	

	public ItemTree(String value, Integer parentId) {
		
		this.value = value;
		this.parentId = parentId;
	}



	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}


	
	public SortedSet<Param> getParams() {
		return params;
	}

	public void setParams(SortedSet<Param> params) {
		this.params = params;
	}

	public Integer getIdItemTree() {
		return idItemTree;
	}

	public void setIdItemTree(Integer idItemTree) {
		this.idItemTree = idItemTree;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public TreeSet<ItemTree> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(TreeSet<ItemTree> childrenList) {
		this.childrenList = childrenList;
	}

	public Set<Param> getParentParams() {
		return parentParams;
	}

	public void setParentParams(SortedSet<Param> parentParams) {
		this.parentParams = parentParams;
	}



	@Override
	public int compareTo(ItemTree o) {
		
		return this.value.compareTo(o.value);
	}

	
	
}
