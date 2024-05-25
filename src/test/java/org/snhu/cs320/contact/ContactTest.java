package org.snhu.cs320.contact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.snhu.cs320.exceptions.ValidationException;

class ContactTest {

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
		"12345678901,First,Last,5553334444,1234 Loblolly Lane", // Too Long ID
		"12345,'',Last,5553334444,1234 Loblolly Lane", // Blank First Name
		"12345,,Last,5553334444,1234 Loblolly Lane", // Null First Name
		"12345,FirstFirstF,Last,5553334444,1234 Loblolly Lane", // Too Long First Name
		"12345,First,'',5553334444,1234 Loblolly Lane", // Blank Last Name
		"12345,First,,5553334444,1234 Loblolly Lane", // Null Last Name
		"12345,First,LastLastLas,5553334444,1234 Loblolly Lane", // Too Long Last Name
		"12345,First,Last,'',1234 Loblolly Lane", // Blank Phone
		"12345,First,Last,,1234 Loblolly Lane", // Null Phone
		"12345,First,Last,55533344449,1234 Loblolly Lane", // Too Long Phone
		"12345,First,Last,555333444A,1234 Loblolly Lane", // Phone with Letters
		"12345,First,Last,555333-444,1234 Loblolly Lane", // Phone with Punctuation
		"12345,First,Last,555333 444,1234 Loblolly Lane", // Phone with Spaces
		"12345,First,Last,5553334444,''", // Blank Address
		"12345,First,Last,5553334444,", // Null Address
		"12345,First,Last,5553334444,1234 Loblolly Lane 1234 Lobloll", // Too Long Address
	})
	void invalidIdThrowsException() {
		assertThatThrownBy(() -> new Contact("", "First", "Last", "5553334444", "1234 Loblolly Lane"))
			.isInstanceOf(ValidationException.class);
	}

}
