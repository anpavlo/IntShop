package com.lviv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "article_Order")
public class ArticleOrder {
	
	@Id
	@GeneratedValue
	@Column(name = "idArticle_Order")
	private Integer idArticleOrder;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "addDate")
	private Date addDate;
	
	@ManyToOne
	@JoinColumn(name="idOrder", nullable=false)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name="idArticle", nullable=false)
	@JsonBackReference
	private Article article;
	
	
	public ArticleOrder() {
		super();
	}


	public Integer getIdArticleOrder() {
		return idArticleOrder;
	}


	public void setIdArticleOrder(Integer idArticleOrder) {
		this.idArticleOrder = idArticleOrder;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Date getAddDate() {
		return addDate;
	}


	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	public Article getArticle() {
		return article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}
	

	

}
