package com.manish.reddit.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Collections.singletonList;

import com.manish.reddit.model.User;
import com.manish.reddit.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with email : " + email));

        return new org.springframework.security
                .core.userdetails.User(user.getEmail(), user.getPassword(),
                user.getEnabled(), true, true,
                true, getAuthorities("USER"));
		
	}

	
	private Collection<? extends GrantedAuthority> getAuthorities(String role){
		return singletonList(new SimpleGrantedAuthority(role));
	}
	
}
