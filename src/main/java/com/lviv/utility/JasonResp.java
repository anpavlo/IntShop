package com.lviv.utility;

import java.io.Serializable;

public class JasonResp implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6420324130942531540L;
	
	public String msg;
	public Boolean status;
	
	public JasonResp(String msg, Boolean status) {
		super();
		this.msg = msg;
		this.status = status;
	}
	
	public JasonResp() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
	
	

}
