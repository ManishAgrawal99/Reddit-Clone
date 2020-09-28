package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Subreddit;

public interface SubredditRepository extends MongoRepository<Subreddit, String> {
	
	Optional<Subreddit> findByName(String subredditName);
}
