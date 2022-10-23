package org.snhu.cs320.entities;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LineItem(
		@NotBlank(message = "ItemNumber is a required field")
		@Size(max = 10, message = "ItemNumber cannot be longer than {max} characters")
		String itemNumber,
		
		@NotNull
		@Min(value = 1, message = "LineItem must have at least {value} quantity")
		Long quantity,
		
		@NotNull
		@DecimalMin(value = "0.01", message = "Price must be at least {value}")
		BigDecimal price
) {

}