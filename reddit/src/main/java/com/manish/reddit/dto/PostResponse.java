package com.manish.reddit.dto;

public class PostResponse {

	private String id;
	private String postName;
	private String url;
	private String description;
	private String username;
	private String subredditName;

	public PostResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostResponse(String id, String postName, String url, String description, String username,
			String subredditName) {
		super();
		this.id = id;
		this.postName = postName;
		this.url = url;
		this.description = description;
		this.username = username;
		this.subredditName = subredditName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSubredditName() {
		return subredditName;
	}

	public void setSubredditName(String subredditName) {
		this.subredditName = subredditName;
	}

	

}
