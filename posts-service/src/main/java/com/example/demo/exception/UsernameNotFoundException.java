package com.example.demo.exception;

public class UsernameNotFoundException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4361494405968519829L;

	public UsernameNotFoundException(String message) {
		super(message);
	}

	
}
