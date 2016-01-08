package com.lviv.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Param")
public class Param implements Serializable, Comparable<Param> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3176605026688479434L;

	@Id
	@Column(name = "idParam")
	@GeneratedValue
	private Integer idParam;

	@Column(name = "paramName")
	private String paramName;

	@ManyToOne
	@JoinColumn(name = "idItemTree", nullable = false)
	@JsonBackReference
	private ItemTree itemTree;

	@OneToMany(mappedBy = "param", fetch = FetchType.EAGER)
	@JsonManagedReference("articleparam-param")
	private Set<ArticleParam> articleParam;

	@Column(name = "priority")
	private Integer priority;

	public Param() {
		super();
	}

	public Param(Integer idParam, String paramName, ItemTree itemTree) {
		super();
		this.idParam = idParam;
		this.paramName = paramName;
		this.itemTree = itemTree;
	}

	public ItemTree getItemTree() {
		return itemTree;
	}

	public void setItemTree(ItemTree itemTree) {
		this.itemTree = itemTree;
	}

	public Integer getIdParam() {
		return idParam;
	}

	public void setIdParam(Integer idParam) {
		this.idParam = idParam;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Set<ArticleParam> getArticleParam() {
		return articleParam;
	}

	public void setArticleParam(Set<ArticleParam> articleParam) {
		this.articleParam = articleParam;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@Override
	public int compareTo(Param o) {

		try {
			if (o.priority != null) {
				return this.priority - o.priority;
			}
		} catch (Exception e) {

		}
		return 0;

	}

	@Override
	public String toString() {

		return "idParam = " + idParam + ";  paramName = " + paramName + "; priority = " + priority + "; itemTree_ID = "
				+ itemTree.getIdItemTree() + "; articleParam = " + articleParam;

	}

}
