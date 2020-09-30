package com.example.demo.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.UsernameNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class AuthService {

	private final UserRepository userRepository;
	
	
	public AuthService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}





	@Transactional(readOnly = true)
	public User getCurrentUser() {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return userRepository.findByEmail(principal.getUsername())
							 .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
	}
}
