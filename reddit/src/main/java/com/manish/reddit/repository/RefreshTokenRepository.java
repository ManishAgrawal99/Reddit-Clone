package com.manish.reddit.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manish.reddit.model.RefreshToken;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {

	Optional<RefreshToken> findByToken(String token);
	void deleteByToken(String token);
}
