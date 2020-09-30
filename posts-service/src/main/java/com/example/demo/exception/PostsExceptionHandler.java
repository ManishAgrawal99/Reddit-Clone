package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PostsExceptionHandler {

	
	@ExceptionHandler(value = PostNotFoundException.class)
	public ResponseEntity<Object> exception(PostNotFoundException exception){
		
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = SubredditNotFoundException.class)
	public ResponseEntity<Object> exception(SubredditNotFoundException exception){
		
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UsernameNotFoundException.class)
	public ResponseEntity<Object> exception(UsernameNotFoundException exception){
		
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
}
