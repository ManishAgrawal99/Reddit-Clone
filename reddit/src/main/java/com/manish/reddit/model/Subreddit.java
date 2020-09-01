package com.manish.reddit.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Subreddits")
public class Subreddit {

	@Id
	private String id;
	
	@NotBlank(message = "Name cannot be blank")
	private String name;
	
	
	@NotBlank(message = "Description cannot be blank")
	private String description;
	
	@DBRef
	private List<Post> posts;
	
	private User user;

	public Subreddit() {
		super();
	}

	public Subreddit(String id, @NotBlank(message = "Name cannot be blank") String name,
			@NotBlank(message = "Description cannot be blank") String description, List<Post> posts, User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.posts = posts;
		this.user = user;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
 	
}
