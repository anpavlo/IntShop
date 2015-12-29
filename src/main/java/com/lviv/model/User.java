package com.lviv.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lviv.model.userdatas.UserAddress;
import com.lviv.model.userdatas.UserEmail;
import com.lviv.model.userdatas.UserPhoneNumber;

@Entity
@Table(name = "user")
public class User {

	
	private Integer idUser;

	
	private String userName;

	
	private String userSurName;

	
	private String userLogin;

	
	private String userPassword;

	
	private Set<UserAddress> userAddress;

	
	private Set<UserPhoneNumber> userPhoneNumber;

	
	private Set<UserEmail> userEmail;
	
	
	private Set<Order> orders;

	public User() {
	}

	public User(Integer idUser, String userName, String userSurName, String userLogin, String userPassword) {

		this.idUser = idUser;
		this.userName = userName;
		this.userSurName = userSurName;
		this.userLogin = userLogin;
		this.userPassword = userPassword;
	}

	@Id
	@GeneratedValue
	@Column(name = "idUser")
	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	@Column(name = "userName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "userSurName")
	public String getUserSurName() {
		return userSurName;
	}

	public void setUserSurName(String userSurName) {
		this.userSurName = userSurName;
	}

	@Column(name = "userLogin")
	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	@Column(name = "userPassword")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public Set<UserAddress> getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Set userAddress) {
		this.userAddress = userAddress;
	}

	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public Set<UserPhoneNumber> getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(Set userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    public Set<UserEmail> getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(Set userEmail) {
		this.userEmail = userEmail;
	}
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	

}
