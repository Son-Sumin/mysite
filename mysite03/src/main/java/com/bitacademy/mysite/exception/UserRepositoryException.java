package com.bitacademy.mysite.exception;

public class UserRepositoryException extends RuntimeException {
	private static
	
	public UserRepositoryException() {
		super("UserRepositoryException Occurs");
	}
	
	public UserRepositoryException(String message) {
		super(message);
	}
}
