package com.lviv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Article_Param")
public class ArticleParam implements Serializable, Comparable<ArticleParam> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3386751079325281910L;

	@Id
	@Column(name = "idArticle_Param")
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "idArticle", nullable = false)
	@JsonBackReference("articleparam-article")
	private Article article;

	@ManyToOne
	@JoinColumn(name = "idParam", nullable = false)
	@JsonBackReference("articleparam-param")
	private Param param;

	@ManyToOne
	@JoinColumn(name = "idParam_Value", nullable = false)
	@JsonBackReference("articleparam-paramvalue")
	private ParamValue paramValue;

	public ArticleParam() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Param getParam() {
		return param;
	}

	public void setParam(Param param) {
		this.param = param;
	}

	public ParamValue getParamValue() {
		return paramValue;
	}

	public void setParamValue(ParamValue paramValue) {
		this.paramValue = paramValue;
	}

	@Override
	public int compareTo(ArticleParam o) {

		if ((this.param != null)&&(o.param!=null)) {
			if (this.param.getPriority() < o.param.getPriority()) {
				return -1;
			}
			if (this.param.getPriority() > o.param.getPriority()) {
				return 1;
			}
		}
		return 0;
	}

}
