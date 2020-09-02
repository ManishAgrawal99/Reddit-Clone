package com.manish.reddit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manish.reddit.dto.CommentsDto;
import com.manish.reddit.model.Comment;
import com.manish.reddit.service.CommentsService;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

	@Autowired
	private CommentsService commentsService;
	
	@PostMapping
	public ResponseEntity<Comment> createComment(@RequestBody CommentsDto commentsDto) {
		
		Comment comment = commentsService.save(commentsDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(comment);
	}
	
	
	@GetMapping
	public ResponseEntity<List<Comment>> getAllComments() {
		
		List<Comment> comments = commentsService.getAllComments();
		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(comments);
	}
	
	@GetMapping("/post/{id}")
	public ResponseEntity<List<Comment>> getAllCommentsForPost(@PathVariable String id) {
		List<Comment> comments = commentsService.getAllCommentsForPost(id);
		
		return ResponseEntity.status(HttpStatus.OK)
				 .body(comments);
	}
	
	
	@GetMapping("/user/{email}")
	public ResponseEntity<List<Comment>> getAllCommentsForUser(@PathVariable String email) {
		List<Comment> comments = commentsService.getAllCommentsForUser(email);
		
		return ResponseEntity.status(HttpStatus.OK)
				 .body(comments);
	}
	
}
