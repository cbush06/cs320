package org.snhu.cs320.validations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;

@ExtendWith(MockitoExtension.class)
public class EntityValidatorTest {
	
	@Mock
	private Validator jakartaValidator;
	
	@InjectMocks
	private EntityValidator validator;
	
	@Test
	void validateAndDoOrThrow_Throws(@Mock ConstraintViolation<Object> violation, @Mock Function<Object, Object> action) {
		when(violation.getMessage()).thenReturn("Oops");
		when(jakartaValidator.validate(any())).thenReturn(Set.of(violation));
		
		assertThatThrownBy(() -> validator.validateAndDoOrThrow(new Object(), action))
			.isInstanceOf(ValidationException.class)
			.hasMessageContaining("Oops");
		
		verify(action, never()).apply(any());
	}
	
	@Test
	void validateAndDoOrThrow_Does(@Mock Function<Object, Object> action) {
		when(jakartaValidator.validate(any())).thenReturn(Collections.emptySet());
		validator.validateAndDoOrThrow(new Object(), action);
		
		verify(action).apply(any());
	}
	
	@Test
	void validate(@Mock ConstraintViolation<Object> violation, @Mock ConstraintViolation<Object> violation2) {
		when(violation.getMessage()).thenReturn("Message 1");
		when(violation2.getMessage()).thenReturn("Message 2");
		
		when(jakartaValidator.validate(any())).thenReturn(Set.of(violation, violation2));
		
		assertThat(validator.validate(new Object())).contains("Message 1", "Message 2");
	}
	
}
