package org.snhu.cs320.contact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.snhu.cs320.exceptions.ValidationException;

public class ContactTest {

	@Test
	void testSuccessPath() {
		Contact contact = new Contact("1", "First", "Last", "5553334444", "1234 Loblolly Lane");
		assertThat(contact)
			.isNotNull()
			.hasFieldOrPropertyWithValue("id", "1")
			.hasFieldOrPropertyWithValue("firstName", "First")
			.hasFieldOrPropertyWithValue("lastName", "Last")
			.hasFieldOrPropertyWithValue("phone", "5553334444")
			.hasFieldOrPropertyWithValue("address", "1234 Loblolly Lane");
	}
	
	@ParameterizedTest
	@CsvSource({
		"'',First,Last,5553334444,1234 Loblolly Lane", // Blank ID
		",First,Last,5553334444,1234 Loblolly Lane", // Null ID
		"12345678901,First,Last,5553334444,1234 Loblolly Lane", // ID is too Long
		"1,First,Last,,1234 Loblolly Lane", // Null Phone
		"1,First,Last,'',1234 Loblolly Lane", // Blank Phone
		"1,First,Last,555333444,1234 Loblolly Lane", // Phone too Short
		"1,First,Last,55533344449,1234 Loblolly Lane", // Phone too Long
		"1,First,Last,555333444A,1234 Loblolly Lane", // Phone with alpha characters
		"1,First,Last,555333444~,1234 Loblolly Lane", // Phone with special characters
		"1,First,Last,55533 4444,1234 Loblolly Lane", // Phone with space
	})
	void invalidInputThrowsException(String id, String firstName, String lastName, String phone, String address) {
		assertThatThrownBy(() -> new Contact(id, firstName, lastName, phone, address))
			.isInstanceOf(ValidationException.class);
	}
	
}
