package com.manish.reddit.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manish.reddit.model.Subreddit;

public interface SubredditRepository extends MongoRepository<Subreddit, Long> {
	
	Optional<Subreddit> findByName(String subredditName);
}
