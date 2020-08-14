package com.manish.reddit.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manish.reddit.model.Post;
import com.manish.reddit.model.User;
import com.manish.reddit.model.Vote;

public interface VoteRepository extends MongoRepository<Vote, Long> {
	
	Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
