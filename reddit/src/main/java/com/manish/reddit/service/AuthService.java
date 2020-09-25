package com.manish.reddit.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.manish.reddit.dto.AuthenticationResponse;
import com.manish.reddit.dto.LoginRequest;
import com.manish.reddit.dto.RegisterRequest;
import com.manish.reddit.exception.SpringRedditException;
import com.manish.reddit.exception.UsernameNotFoundException;
import com.manish.reddit.model.NotificationEmail;
import com.manish.reddit.model.RefreshTokenRequest;
import com.manish.reddit.model.User;
import com.manish.reddit.model.VerificationToken;
import com.manish.reddit.producer.LibraryEventProducer;
import com.manish.reddit.repository.UserRepository;
import com.manish.reddit.repository.VerificationTokenRepository;
import com.manish.reddit.security.JwtProvider;

@Service
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	private final RefreshTokenService refreshTokenService;
//	private final LibraryEventProducer libraryEventProducer;

	

	

	public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository,
			VerificationTokenRepository verificationTokenRepository, MailService mailService,
			AuthenticationManager authenticationManager, JwtProvider jwtProvider,
			RefreshTokenService refreshTokenService //, LibraryEventProducer libraryEventProducer
			) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.verificationTokenRepository = verificationTokenRepository;
		this.mailService = mailService;
		this.authenticationManager = authenticationManager;
		this.jwtProvider = jwtProvider;
		this.refreshTokenService = refreshTokenService;
//		this.libraryEventProducer = libraryEventProducer;
	}

	@Transactional
	public void signup(RegisterRequest registerRequest) {

		User user1 = userRepository.findByEmail(registerRequest.getEmail()).orElse(null);
		
		if (user1 != null) {
			
			throw new SpringRedditException("The email is already registered");
		}

		User user = new User();

		user.setUserName(registerRequest.getUserName());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

		user.setCreated(Instant.now());
		user.setEnabled(false);

		userRepository.save(user);

		String token = generateVerificationToken(user);

		NotificationEmail notificationEmail = new NotificationEmail("Please Activate your Account", user.getEmail(),
				"Thank you for signing up to Spring Reddit, "
						+ "please click on the below url to activate your account : "
						+ "http://localhost:8080/api/auth/accountVerification/" + token);
		
//		try {
//			libraryEventProducer.sendMailEvent(notificationEmail);
//		} catch (JsonProcessingException e) {
//			System.out.println("Issue at AuthService while Sending kafka Message");
//		}
		
		mailService.sendMail(notificationEmail);

	}

	private String generateVerificationToken(User user) {
		String verString = UUID.randomUUID().toString();

		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(verString);
		verificationToken.setUser(user);

		verificationTokenRepository.save(verificationToken);

		return verString;

	}

	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);

		verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token"));

		fetchUserAndEnable(verificationToken.get());
	}

	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {
		String email = verificationToken.getUser().getEmail();
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new SpringRedditException("User not found with email id --->" + email));

		user.setEnabled(true);
		userRepository.save(user);
	}
	
	
	@Transactional
	public AuthenticationResponse login(LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		Instant expiresAt = Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis());
		
		return new AuthenticationResponse(token, loginRequest.getEmail(), refreshTokenService.generateRegreshToken().getToken(), expiresAt);

	}
	
	

	@Transactional(readOnly = true)
	public User getCurrentUser() {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return userRepository.findByEmail(principal.getUsername())
							 .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
	}

	public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		
		refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
		
		String token = jwtProvider.generateTokenWithUsername(refreshTokenRequest.getEmail());
		
		return new AuthenticationResponse(token, refreshTokenRequest.getEmail(), refreshTokenRequest.getRefreshToken(), 
										  Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()));
	}
}
