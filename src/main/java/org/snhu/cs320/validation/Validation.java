package org.snhu.cs320.validation;

import org.snhu.cs320.exceptions.ValidationException;

public class Validation {
	
	private Validation() {}
	
	public static void validateNotNull(Object input, String label) {
		if (input == null) {
			throw new ValidationException(label + " must not be null");
		}
	}
	
	public static void validateNotBlank(String input, String label) {
		validateNotNull(input, label);
		
		if (input.trim().length() < 1) {
			throw new ValidationException(label + " must not be blank");
		}
	}
	
	public static void validateLength(String input, String label, int minLength, int maxLength) {
		if (input.length() < minLength || input.length() > maxLength) {
			throw new ValidationException(label + " must be at least " + minLength + " and no greater than " + maxLength + " characters in length");
		}
	}
	
	public static void validateNumeric(String input, String label) {
		if (input.matches("\\D+")) {
			throw new ValidationException(label + " must only contain digits");
		}
	}
	
}
