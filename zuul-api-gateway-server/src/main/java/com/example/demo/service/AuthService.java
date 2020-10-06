package com.example.demo.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.exception.SpringRedditException;
import com.example.demo.model.NotificationEmail;
import com.example.demo.model.User;
import com.example.demo.model.VerificationToken;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VerificationTokenRepository;
import com.example.demo.security.JwtProvider;


@Service
public class AuthService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private JwtProvider jwtProvider;
	
	
	@Transactional
	public void signup(RegisterRequest registerRequest) {
		
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
						+ "http://localhost:8000/api/auth/accountVerification/" + token);
		
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



	public AuthenticationResponse login(LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext()
							 .setAuthentication(authentication);
	
		String token = jwtProvider.generateToken(authentication);
		
		return new AuthenticationResponse(token, loginRequest.getEmail());
	}
	
	
	
}
