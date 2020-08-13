package com.manish.reddit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.time.Instant;

import javax.validation.constraints.NotBlank;

@Document(collection = "Posts")
public class Post {

	@Id
	private long postId;
	
	
	@NotBlank(message = "Post Name Cannot be Blank")
	private String postName;
	
	@Nullable
	private String url;
	
	
	@Nullable
	private String description;
	
	private Integer voteCount = 0;
	
	private User user;
	
	private Instant createdDate;
	
	
	private Subreddit subreddit;


	public Post(long postId, @NotBlank(message = "Post Name Cannot be Blank") String postName, String url,
			String description, Integer voteCount, User user, Instant createdDate, Subreddit subreddit) {
		super();
		this.postId = postId;
		this.postName = postName;
		this.url = url;
		this.description = description;
		this.voteCount = voteCount;
		this.user = user;
		this.createdDate = createdDate;
		this.subreddit = subreddit;
	}


	public Post() {
		
	}


	public long getPostId() {
		return postId;
	}


	public void setPostId(long postId) {
		this.postId = postId;
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


	public Integer getVoteCount() {
		return voteCount;
	}


	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Instant getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}


	public Subreddit getSubreddit() {
		return subreddit;
	}


	public void setSubreddit(Subreddit subreddit) {
		this.subreddit = subreddit;
	}
	
	
	
	
	
	
	
	
}
