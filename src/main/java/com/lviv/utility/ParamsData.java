package com.lviv.utility;

import java.io.Serializable;

public class ParamsData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6439639885792653010L;
	private String paramName;
	private String paramValue;
	private String paramUnits;
	
	public ParamsData(String paramName, String paramValue, String paramUnits) {
		
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.paramUnits = paramUnits;
	}

	public ParamsData() {
		
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamUnits() {
		return paramUnits;
	}

	public void setParamUnits(String paramUnits) {
		this.paramUnits = paramUnits;
	}
	
	
	
	

}
