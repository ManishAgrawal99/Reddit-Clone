package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.User;



public interface UserRepository extends MongoRepository<User, Long> {
	
	Optional<User> findByUserName(String userName);
	Optional<User> findByEmail(String email);
	

}
