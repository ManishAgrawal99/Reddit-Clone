package com.manish.reddit.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manish.reddit.dto.PostRequest;
import com.manish.reddit.exception.PostNotFoundException;
import com.manish.reddit.exception.SubredditNotFoundException;
import com.manish.reddit.model.Post;
import com.manish.reddit.model.Subreddit;
import com.manish.reddit.model.User;
import com.manish.reddit.repository.PostRepository;
import com.manish.reddit.repository.SubredditRepository;

@Service
public class PostService {

	private final SubredditRepository subredditRepository;
	private final PostRepository postRepository;
	private final AuthService authService;
	
	
	
	public PostService(SubredditRepository subredditRepository, AuthService authService, PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
		this.subredditRepository = subredditRepository;
		this.authService = authService;
	}

	
	@Transactional
	public Post save(PostRequest postRequest) {
		Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
						   .orElseThrow(()->new SubredditNotFoundException(postRequest.getSubredditName() +" not found"));
	
		User user = authService.getCurrentUser();
		
		Post newPost = new Post();
		newPost.setPostName(postRequest.getPostName());
		newPost.setDescription(postRequest.getDescription());
		newPost.setUser(user);
		newPost.setCreatedDate(Instant.now());
		newPost.setSubredditId(subreddit.getId());
		
		Post post = postRepository.save(newPost);
		post.setUrl("/api/posts/"+post.getPostId());
		
		List<Post> subPosts = subreddit.getPosts();
		
		if(subPosts == null) {
			subPosts = new ArrayList<>();
		}
		
		subPosts.add(post);
		
		subreddit.setPosts(subPosts);
		
		subredditRepository.save(subreddit);
		
		return postRepository.save(post);
	}
	
	
	public Post getPost(String id) {
		Post post = postRepository.findById(id).orElseThrow(()->new PostNotFoundException("Cannot find Post with ID: "+id));
		
		return post;
	}
	
	
	public List<Post> getAllPosts(){
		return postRepository.findAll();
	}
	
	
	public List<Post> getPostsBySubreddit(String id){
		Subreddit sub = subredditRepository.findById(id).orElseThrow(()->new SubredditNotFoundException("Cannot find Post with ID: "+id));
		
		return sub.getPosts();
	}
	
}
