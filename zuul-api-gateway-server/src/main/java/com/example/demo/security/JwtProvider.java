package com.example.demo.security;

import static io.jsonwebtoken.Jwts.parser;
import static java.util.Date.from;

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

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.demo.exception.SpringRedditException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtProvider {

	private KeyStore keyStore;
	
	private Long jwtExpirationInMillis = 900000L;

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

		Claims claims = parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();

		return claims.getSubject();
	}
}
