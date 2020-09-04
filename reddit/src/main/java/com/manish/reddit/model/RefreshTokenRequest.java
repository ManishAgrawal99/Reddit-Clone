package com.manish.reddit.model;

import javax.validation.constraints.NotBlank;

public class RefreshTokenRequest {

	@NotBlank
	private String refreshToken;
	private String email;

	public RefreshTokenRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RefreshTokenRequest(@NotBlank String refreshToken, String email) {
		super();
		this.refreshToken = refreshToken;
		this.email = email;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
}
