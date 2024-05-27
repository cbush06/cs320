package org.snhu.cs320.appointment;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class AppointmentTest {
	
	@Test
	void testSuccessPath() {
		Appointment appt = new Appointment("1", LocalDate.now(), "Description");
		assertThat(appt)
			.isNotNull()
			.hasFieldOrPropertyWithValue("id", "1")
			.hasFieldOrPropertyWithValue("date", LocalDate.now())
			.hasFieldOrPropertyWithValue("description", "Description");
	}
	
}
