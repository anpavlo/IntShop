package com.lviv.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "orders")
public class Order {
	

	
	@Id
	@GeneratedValue
	@Column(name = "idOrder")
	private Integer idOrder;
	
	
	@Column(name = "openDate")
	private Date openDate;
	
	@Column(name = "closeDate")
	private Date closeDate;
	
	@ManyToOne
	@JoinColumn(name="idUser", nullable=false)
	private User user;
	
	@Column(name = "totalPricel")
	private Double totalPricel;
	
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER)
	private Set<ArticleOrder> articleOrders;
	
	
	
	public Order() {}


	public Integer getIdOrder() {
		return idOrder;
	}


	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}


	public Date getOpenDate() {
		return openDate;
	}


	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}


	public Date getCloseDate() {
		return closeDate;
	}


	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Double getTotalPricel() {
		return totalPricel;
	}


	public void setTotalPricel(Double totalPricel) {
		this.totalPricel = totalPricel;
	}

	
	public Set<ArticleOrder> getArticleOrders() {
		return articleOrders;
	}


	public void setArticleOrders(Set<ArticleOrder> articleOrders) {
		this.articleOrders = articleOrders;
	}


	
	
	
	
	
} 