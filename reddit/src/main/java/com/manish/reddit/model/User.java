package com.manish.reddit.model;

import java.time.Instant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {
	
//	@Id
//	private String userId;
	
	
	@NotBlank(message = "Username is Required")
	private String userName;
	
	
	@NotBlank(message = "Password is Required")
	private String password;
	
	@Id
	@Email
	@NotEmpty(message = "Email is required")
	private String email;
	
	
	private Instant created;
	
	private Boolean enabled;

	public User(@NotBlank(message = "Username is Required") String userName,
			@NotBlank(message = "Password is Required") String password,
			@Email @NotEmpty(message = "Email is required") String email, Instant created, Boolean enabled) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.created = created;
		this.enabled = enabled;
	}

	public User() {
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Instant getCreated() {
		return created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
	
}
