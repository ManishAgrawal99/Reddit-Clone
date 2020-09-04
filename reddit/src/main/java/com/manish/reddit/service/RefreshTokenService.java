package com.manish.reddit.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manish.reddit.exception.SpringRedditException;
import com.manish.reddit.model.RefreshToken;
import com.manish.reddit.repository.RefreshTokenRepository;

@Service
@Transactional
public class RefreshTokenService {
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	
	public RefreshToken generateRegreshToken() {
		
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setCreatedDate(Instant.now());
		
		
		return refreshTokenRepository.save(refreshToken);
		
	}
	
	public void validateRefreshToken(String token) {
		
		refreshTokenRepository.findByToken(token)
							  .orElseThrow(()-> new SpringRedditException("Invalid Refresh Token"));
	}
	
	public void deleteRefreshToken(String token) {
		
		refreshTokenRepository.deleteByToken(token);
	}
}
