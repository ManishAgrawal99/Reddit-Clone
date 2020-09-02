package com.manish.reddit.model;

import java.time.Instant;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Comments")
public class Comment {
	
	@Id
	private String id;

	@NotEmpty
	private String text;
	
	private String postId;
	
	private Instant createdDate;
	
	private User user;

	public Comment(String id, @NotEmpty String text, String postId, Instant createdDate, User user) {
		super();
		this.id = id;
		this.text = text;
		this.postId = postId;
		this.createdDate = createdDate;
		this.user = user;
	}
	
	

	public Comment() {
		super();
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPostId() {
		return postId;
	}

	public void setPost(String postId) {
		this.postId = postId;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
