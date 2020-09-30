package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Post;
import com.example.demo.model.User;


public interface PostRepository extends MongoRepository<Post, String> {

	//List<Post> findAllBySubreddit(Subreddit subreddit);
	
	List<Post> findByUser(User user);
}
