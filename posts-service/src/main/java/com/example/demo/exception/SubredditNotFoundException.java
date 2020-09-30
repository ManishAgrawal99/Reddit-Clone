package com.example.demo.exception;

public class SubredditNotFoundException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3200101240109069216L;

	/**
	 * 
	 */
	

	public SubredditNotFoundException(String message) {
        super(message);
    }
}