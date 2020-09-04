package com.manish.reddit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Votes")
public class Vote {

	@Id
	private String voteId;

	private VoteType voteType;

	private String postId;

	private String userEmail;

	public Vote() {
		super();
	}

	

	public Vote(String voteId, VoteType voteType, String postId, String userEmail) {
		super();
		this.voteId = voteId;
		this.voteType = voteType;
		this.postId = postId;
		this.userEmail = userEmail;
	}



	public String getVoteId() {
		return voteId;
	}

	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	public VoteType getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}



	public String getUserEmail() {
		return userEmail;
	}



	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	

	
}
