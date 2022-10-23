package org.snhu.cs320.entities;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PurchaseOrder(
		@NotBlank(message = "PurchaseId is a required field")
		@Size(max = 10, message = "PurchaseId cannot be longer than {max} characters")
		String purchaseId,
		
		@NotBlank(message = "CustomerId is a required field")
		@Size(max = 10, message = "CustomerId cannot be longer than {max} characters")
		String customerId,
		
		@NotBlank(message = "Phone is a required field")
		@Pattern(regexp = "\\d{10}", message = "Phone must be exactly 10 digits")
		String contactPhone,
		
		@NotEmpty(message = "PurchaseOrder must have at least 1 LineItem")
		List<LineItem> lineItems
) {
	
}
