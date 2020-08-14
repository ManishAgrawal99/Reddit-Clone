package com.manish.reddit.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manish.reddit.model.VerificationToken;

public interface VerificationTokenRepository extends MongoRepository<VerificationToken, Long> {
	
	Optional<VerificationToken> findByToken(String token);
}
