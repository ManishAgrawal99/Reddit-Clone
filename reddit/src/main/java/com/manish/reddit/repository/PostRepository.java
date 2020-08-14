package com.manish.reddit.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manish.reddit.model.Post;
import com.manish.reddit.model.Subreddit;
import com.manish.reddit.model.User;

public interface PostRepository extends MongoRepository<Post, Long> {

	List<Post> findAllBySubreddit(Subreddit subreddit);
	
	List<Post> findByUser(User user);
}
