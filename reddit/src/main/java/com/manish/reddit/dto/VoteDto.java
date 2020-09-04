package com.manish.reddit.dto;

import com.manish.reddit.model.VoteType;

public class VoteDto {

	private VoteType voteType;
	private String postId;

	public VoteDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public VoteDto(VoteType voteType, String postId) {
		super();
		this.voteType = voteType;
		this.postId = postId;
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

}
