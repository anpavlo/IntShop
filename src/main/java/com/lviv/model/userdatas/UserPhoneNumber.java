package com.lviv.model.userdatas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lviv.model.User;

@Entity
@Table(name = "userPhoneNumber")
public class UserPhoneNumber {
	
	@Id
	@Column(name = "idUserPhoneNumber")
	@GeneratedValue
	private Integer idUserPhoneNumber;
	
	@Column(name = "userPhoneNumber")
	private String userPhoneNum;
	
	@Column(name = "phoneNumberType")
	private String phoneNumberType;
	
	@ManyToOne
    @JoinColumn(name="idUser", nullable=false)
	private User user;
	
	
	
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserPhoneNumber() {
	}

	public String getUserPhoneNum() {
		return userPhoneNum;
	}

	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}

	public Integer getIdUserPhoneNumber() {
		return idUserPhoneNumber;
	}

	public void setIdUserPhoneNumber(Integer idUserPhoneNumber) {
		this.idUserPhoneNumber = idUserPhoneNumber;
	}

	public String getPhoneNumberType() {
		return phoneNumberType;
	}

	public void setPhoneNumberType(String phoneNumberType) {
		this.phoneNumberType = phoneNumberType;
	}

}
