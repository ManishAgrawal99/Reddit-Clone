package com.manish.reddit.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manish.reddit.model.User;

public interface UserRepository extends MongoRepository<User, Long> {
	
	Optional<User> findByUserName(String userName);
	Optional<User> findByEmail(String email);
	

}
