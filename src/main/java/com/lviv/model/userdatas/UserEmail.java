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
@Table(name = "userEmail")
public class UserEmail {
	
	@Id
	@Column(name = "idUserEmail")
	@GeneratedValue
	private Integer idUserEmail;
	
	
	@Column(name = "userEmail")
	private String userEmail;
	
	
	@Column(name = "userEmailType")
	private String userEmailType;
	
	
	@ManyToOne
    @JoinColumn(name="idUser", nullable=false)
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserEmail() {
	}

	public Integer getIdUserEmail() {
		return idUserEmail;
	}

	public void setIdUserEmail(Integer idUserEmail) {
		this.idUserEmail = idUserEmail;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserEmailType() {
		return userEmailType;
	}

	public void setUserEmailType(String userEmailType) {
		this.userEmailType = userEmailType;
	}
	
	

}	

