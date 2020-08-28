package com.manish.reddit.dto;

public class AuthenticationResponse {

	private String authenticationToken;
	private String email;

	public AuthenticationResponse(String authenticationToken, String email) {
		super();
		this.authenticationToken = authenticationToken;
		this.email = email;
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
	
	

}
