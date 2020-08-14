package com.manish.reddit.model;

import java.time.Instant;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Comments")
public class Comment {
	
	@Id
	private Long id;

	@NotEmpty
	private String text;
	
	private Post post;
	
	private Instant createdDate;
	
	private User user;

	public Comment(Long id, @NotEmpty String text, Post post, Instant createdDate, User user) {
		super();
		this.id = id;
		this.text = text;
		this.post = post;
		this.createdDate = createdDate;
		this.user = user;
	}
	
	

	public Comment() {
		super();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
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
