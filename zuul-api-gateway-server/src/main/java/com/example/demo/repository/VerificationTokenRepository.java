package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.VerificationToken;


public interface VerificationTokenRepository extends MongoRepository<VerificationToken, String> {
	
	Optional<VerificationToken> findByToken(String token);
}
