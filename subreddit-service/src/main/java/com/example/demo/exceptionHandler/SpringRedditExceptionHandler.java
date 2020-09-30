package com.example.demo.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.SpringRedditException;

@ControllerAdvice
public class SpringRedditExceptionHandler {

	@ExceptionHandler(value = SpringRedditException.class)
	public ResponseEntity<Object> exception(SpringRedditException exception){
		
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
}
