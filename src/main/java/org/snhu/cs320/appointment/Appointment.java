package org.snhu.cs320.appointment;

import java.time.LocalDate;

import org.snhu.cs320.validation.Validation;

public class Appointment {
	
	private String id;
	private LocalDate date;
	private String description;
	
	public Appointment(String id, LocalDate date, String description) {
		super();
		this.id = id;
		this.date = date;
		this.description = description;
	}
	
	void validate() {
		// ID
		Validation.validateNotBlank(id, "id");
		Validation.validateLength(id, "id", 1, 10);
		
		// Validate Date
		Validation.validateNotNull(date, "date");
		Validation.validateIsPresentOrFuture(date, "date");
		
		// Validate Description
		Validation.validateNotBlank(description, "description");
		Validation.validateLength(description, "description", 1, 50);
		
	}

}
