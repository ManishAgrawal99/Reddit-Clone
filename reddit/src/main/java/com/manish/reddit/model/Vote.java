package com.manish.reddit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Votes")
public class Vote {

	@Id
	private Long voteId;

	private VoteType voteType;

	private Post post;

	private User user;

	public Vote() {
		super();
	}

	public Vote(Long voteId, VoteType voteType, Post post, User user) {
		super();
		this.voteId = voteId;
		this.voteType = voteType;
		this.post = post;
		this.user = user;
	}

	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public VoteType getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
