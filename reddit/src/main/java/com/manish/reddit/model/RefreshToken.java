package com.manish.reddit.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "RefreshTokens")
public class RefreshToken {

	@Id
	private String id;
	private String token;
	private Instant createdDate;

	
	
	public RefreshToken() {
		super();
	}

	public RefreshToken(String id, String token, Instant createdDate) {
		super();
		this.id = id;
		this.token = token;
		this.createdDate = createdDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	

}
