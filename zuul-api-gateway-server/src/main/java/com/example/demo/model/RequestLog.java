package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "RequestLogs")
public class RequestLog {

	private String method;
	private String requestURL;
	private String userEmail;

	public RequestLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestLog(String method, String requestURL, String userEmail) {
		super();
		this.method = method;
		this.requestURL = requestURL;
		this.userEmail = userEmail;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	

	

}
