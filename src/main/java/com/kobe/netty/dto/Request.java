package com.kobe.netty.dto;

import java.io.Serializable;

public class Request implements Serializable{
	private String id ;
	private String name ;
	private String requestBody ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}


}
