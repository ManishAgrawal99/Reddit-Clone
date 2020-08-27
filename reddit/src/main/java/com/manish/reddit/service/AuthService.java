package com.manish.reddit.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manish.reddit.dto.RegisterRequest;
import com.manish.reddit.model.NotificationEmail;
import com.manish.reddit.model.User;
import com.manish.reddit.model.VerificationToken;
import com.manish.reddit.repository.UserRepository;
import com.manish.reddit.repository.VerificationTokenRepository;

@Service
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;
	
	
	@Autowired
	public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, MailService mailService) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.verificationTokenRepository = verificationTokenRepository;
		this.mailService = mailService;
	}




	@Transactional
	public void signup( RegisterRequest registerRequest) {
		
		User user = new User();
		
		user.setUserName(registerRequest.getUserName());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		
		user.setCreated(Instant.now());
		user.setEnabled(false);
		
		userRepository.save(user);
		
		String token = generateVerificationToken(user);
		
		mailService.sendMail(new NotificationEmail("Please Activate your Account",
					user.getEmail(),
					"Thank you for signing up to Spring Reddit, " +
			        "please click on the below url to activate your account : " +
	                "http://localhost:8080/api/auth/accountVerification/" + token));
		
	}





	private String generateVerificationToken(User user) {
		String verString = UUID.randomUUID().toString();
		
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(verString);
		verificationToken.setUser(user);
		
		verificationTokenRepository.save(verificationToken);
		
		return verString;
		
	}
}
