package com.example.demo.model;

import java.time.Instant;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

@Document(collection = "Posts")
public class Post {
	
	@Id
	private String postId;

	@NotBlank(message = "Post Name Cannot be Blank")
	private String postName;

	@Nullable
	private String url;

	@Nullable
	private String description;

	private Integer voteCount = 0;

	private User user;

	private Instant createdDate;

	private String subredditId;

	@DBRef
	private List<Comment> comments;

	public Post(String postId, @NotBlank(message = "Post Name Cannot be Blank") String postName, String url,
			String description, Integer voteCount, User user, Instant createdDate, String subredditId,
			List<Comment> comments) {
		super();
		this.postId = postId;
		this.postName = postName;
		this.url = url;
		this.description = description;
		this.voteCount = voteCount;
		this.user = user;
		this.createdDate = createdDate;
		this.subredditId = subredditId;
		this.comments = comments;
	}

	public Post() {

	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
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

	public String getSubredditId() {
		return subredditId;
	}

	public void setSubredditId(String subredditId) {
		this.subredditId = subredditId;
	}
}
