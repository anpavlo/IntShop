package com.lviv.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Param_Value")
public class ParamValue implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8569294416862703553L;

	@Id
	@Column(name="idParam_Value")
	@GeneratedValue
	private Integer idParamValue;
	
	@Column(name="paramValue")
	private String paramValue;
	
	@Column(name="units")
	private String units;
	
	@OneToMany(mappedBy="paramValue", fetch=FetchType.EAGER)
	@JsonManagedReference("articleparam-paramvalue")
	private Set<ArticleParam> articleParam;
	
	
	
	public ParamValue() {
		super();
	}
	public Integer getIdParamValue() {
		return idParamValue;
	}
	public void setIdParamValue(Integer idParamValue) {
		this.idParamValue = idParamValue;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}

	public Set<ArticleParam> getArticleParam() {
		return articleParam;
	}

	public void setArticleParam(Set<ArticleParam> articleParam) {
		this.articleParam = articleParam;
	}
	
	

}
