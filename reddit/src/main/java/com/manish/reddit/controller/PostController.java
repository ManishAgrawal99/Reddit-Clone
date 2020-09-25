package com.manish.reddit.controller;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manish.reddit.dto.PostRequest;
import com.manish.reddit.dto.PostResponse;
import com.manish.reddit.model.Post;
import com.manish.reddit.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping
	public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest) {
		Post savedPost = postService.save(postRequest);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(savedPost);
	}
	
	
	//@Cacheable(key = "#id", value = "posts", unless = "#result.voteCount < 1000")
	@GetMapping("/{id}")
	public ResponseEntity<Post> getPost(@PathVariable String id) {
		
		Post post = postService.getPost(id);
		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(post);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Post>> getAllPosts(){
		
		List<Post> posts = postService.getAllPosts();
		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(posts);
	}
	
	@GetMapping("/by-subreddit/{id}")
	public ResponseEntity<List<Post>> getPostsBySubreddit(@PathVariable String id){
		List<Post> posts = postService.getPostsBySubreddit(id);
		
		return ResponseEntity.status(HttpStatus.OK)
				 .body(posts);
	}
}
