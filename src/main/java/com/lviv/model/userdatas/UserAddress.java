package com.lviv.model.userdatas;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lviv.model.User;

@Entity
@Table(name = "useraddress")
public class UserAddress {
	
	@Id
	@Column(name = "idUserAddress")
	@GeneratedValue
	private Integer idUserAddress;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "zipcode")
	private String zipcode;
	
	@ManyToOne
	@JoinColumn(name="idUser", nullable=false)
	private User user;
	

	
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserAddress() {}

	public UserAddress(String street, String city, 
	                  String state, String zipcode) {
	      this.street = street; 
	      this.city = city; 
	      this.state = state; 
	      this.zipcode = zipcode; 
	   }


	public Integer getIdUserAddress() {
		return idUserAddress;
	}

	public void setIdUserAddress(Integer idUserAddress) {
		this.idUserAddress = idUserAddress;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
