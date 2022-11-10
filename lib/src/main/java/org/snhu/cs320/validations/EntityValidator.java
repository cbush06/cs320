package org.snhu.cs320.validations;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;

public class EntityValidator {
	
	private final Validator validator;
	
	public EntityValidator() {
		this(Validation.buildDefaultValidatorFactory().getValidator());
	}
	
	public EntityValidator(final Validator validator) {
		this.validator = validator;
	}
	
	public <T> T validateAndDoOrThrow(final T entity, final Function<T, T> action) {
		final List<String> violations = validate(entity);
		
		if(violations.isEmpty()) {
			return action.apply(entity);
		}
		
		final String errorMessage = String.format(
				"Validation for entity of type %s failed:\n%s",
				entity.getClass().getSimpleName(),
				String.join("\n", violations)
		);
		
		throw new ValidationException(errorMessage);
	}
	
	public <T> List<String> validate(final T entity) {
		final Set<ConstraintViolation<T>> errors = validator.validate(entity);
		return errors.stream()
				.map(ConstraintViolation::getMessage)
				.collect(Collectors.toUnmodifiableList());
	}
	
}
