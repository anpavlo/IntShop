package com.lviv.model;

import java.io.Serializable;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Article")
public class Article implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2475404905362541646L;

	@Id
	@Column(name = "idArticle")
	@GeneratedValue
    private Integer idArticle;
	
	@Column(name = "articleName")
    private String articleName;
    
	@Column(name = "articleDescription")
	@Type(type="text")
    private String articleDescription;
    
	@Column(name = "articleQuantity")
    private Integer articleQuantity;
    
	@Column(name = "articlePhotoPath")
    private String articlePathToPhoto;
    
	@Column(name = "articlePrice")
    private Double articlePrice;
    
	@OneToMany(mappedBy="article", fetch=FetchType.EAGER)
	@JsonManagedReference
    private Set<ArticleOrder> articleOrders;
	
	@OneToMany(mappedBy="article", fetch=FetchType.EAGER) 
	@javax.persistence.OrderBy("param")
	@JsonManagedReference("articleparam-article")
	private SortedSet<ArticleParam> articleParam;
	
	
	@ManyToOne
	@JoinColumn(name="idItemTree", nullable=false)
	@JsonBackReference
	private ItemTree itemTree;




	public SortedSet<ArticleParam> getArticleParam() {
		return articleParam;
	}




	public void setArticleParam(SortedSet<ArticleParam> articleParam) {
		this.articleParam = articleParam;
	}




	public Article() {
		super();
	}




	public Integer getIdArticle() {
		return idArticle;
	}




	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}




	public String getArticleName() {
		return articleName;
	}




	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}




	public String getArticleDescription() {
		return articleDescription;
	}




	public void setArticleDescription(String articleDescription) {
		this.articleDescription = articleDescription;
	}




	public Integer getArticleQuantity() {
		return articleQuantity;
	}




	public void setArticleQuantity(Integer articleQuantity) {
		this.articleQuantity = articleQuantity;
	}




	public String getArticlePathToPhoto() {
		return articlePathToPhoto;
	}




	public void setArticlePathToPhoto(String articlePathToPhoto) {
		this.articlePathToPhoto = articlePathToPhoto;
	}




	public Double getArticlePrice() {
		return articlePrice;
	}




	public void setArticlePrice(Double articlePrice) {
		this.articlePrice = articlePrice;
	}




	public Set<ArticleOrder> getArticleOrders() {
		return articleOrders;
	}




	public void setArticleOrders(Set<ArticleOrder> articleOrders) {
		this.articleOrders = articleOrders;
	}




	public ItemTree getItemTree() {
		return itemTree;
	}




	public void setItemTree(ItemTree itemTree) {
		this.itemTree = itemTree;
	}
	    
	

 
}
