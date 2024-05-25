package org.snhu.cs320.exceptions;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -44833535862295802L;

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}
	

}
