package com.manish.reddit.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.manish.reddit.exception.SpringRedditException;
import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import static io.jsonwebtoken.Jwts.parser;
import static java.util.Date.from;

@Service
public class JwtProvider {
	
	private KeyStore keyStore;
	
	@Value("${jwt.expiration.time}")
	private Long jwtExpirationInMillis;
	
	@PostConstruct
	public void init() {
		try {
			keyStore = keyStore.getInstance("JKS");
			InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
			keyStore.load(resourceAsStream, "secret".toCharArray());
			
		} catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
			throw new SpringRedditException("Exception occured while loading key store");
		}
	}
	
	
	public String generateToken(Authentication authentication) {
		User principal = (User) authentication.getPrincipal();
		
		return Jwts.builder()
				   .setSubject(principal.getUsername())
				   .signWith(getPrivateKey())
				   .setIssuedAt(from(Instant.now()))
				   .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
				   .compact();
	}
	
	
	public String generateTokenWithUsername(String username) {
		
		return Jwts.builder()
				   .setSubject(username)
				   .signWith(getPrivateKey())
				   .setIssuedAt(from(Instant.now()))
				   .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
				   .compact();
	}
	
	
	private PrivateKey getPrivateKey() {
		try {
			return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
			throw new SpringRedditException("Exception ocured while retrieving public key from keystore");
		}
	}
	
	
	public boolean validateToken(String jwt) {
		parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
		return true;
	}


	private PublicKey getPublicKey() {
		try {
			return keyStore.getCertificate("springblog").getPublicKey();
		} catch (KeyStoreException e) {
			throw new SpringRedditException("Exception occured while retrieving public key from key store");
		}
	}
	
	public String getEmailFromJwt(String token) {
		
		Claims claims = parser().setSigningKey(getPublicKey())
								.parseClaimsJws(token)
								.getBody();
		
		return claims.getSubject();
	}


	public Long getJwtExpirationInMillis() {
		return jwtExpirationInMillis;
	}
	
	
}
