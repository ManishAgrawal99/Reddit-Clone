package com.manish.reddit.dto;

import java.time.Instant;

public class AuthenticationResponse {

	private String authenticationToken;
	private String email;
	private String refreshToken;
	private Instant expiresAt;

	public AuthenticationResponse(String authenticationToken, String email) {
		super();
		this.authenticationToken = authenticationToken;
		this.email = email;
	}
	
	

	public AuthenticationResponse(String authenticationToken, String email, String refreshToken, Instant expiresAt) {
		super();
		this.authenticationToken = authenticationToken;
		this.email = email;
		this.refreshToken = refreshToken;
		this.expiresAt = expiresAt;
	}



	public AuthenticationResponse() {
		super();
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getRefreshToken() {
		return refreshToken;
	}



	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}



	public Instant getExpiresAt() {
		return expiresAt;
	}



	public void setExpiresAt(Instant expiresAt) {
		this.expiresAt = expiresAt;
	}
	
	
	

}
