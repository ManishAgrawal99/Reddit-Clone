package com.manish.reddit.dto;

public class CommentsDto {

	private String postId;
	private String text;
	private String userEmail;

	public CommentsDto(String postId, String text, String userEmail) {
		super();
		this.postId = postId;
		this.text = text;
		this.userEmail = userEmail;
	}

	public CommentsDto() {
		super();
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	

	
	
}
