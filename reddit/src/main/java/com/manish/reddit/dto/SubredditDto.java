package com.manish.reddit.dto;

public class SubredditDto {

	private String id;
	private String subredditName;
	private String description;
	private Integer numberOfPosts;

	
	
	public SubredditDto() {
		super();
	}

	public SubredditDto(String id, String subredditName, String description, Integer numberOfPosts) {
		super();
		this.id = id;
		this.subredditName = subredditName;
		this.description = description;
		this.numberOfPosts = numberOfPosts;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubredditName() {
		return subredditName;
	}

	public void setSubredditName(String subredditName) {
		this.subredditName = subredditName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNumberOfPosts() {
		return numberOfPosts;
	}

	public void setNumberOfPosts(Integer numberOfPosts) {
		this.numberOfPosts = numberOfPosts;
	}

	
	
}
