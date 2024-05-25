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
		"'',First,Last,5553334444,1234 Loblolly Lane",
		",First,Last,5553334444,1234 Loblolly Lane",
		""
	})
	void invalidIdThrowsException() {
		assertThatThrownBy(() -> new Contact("", "First", "Last", "5553334444", "1234 Loblolly Lane"))
			.isInstanceOf(ValidationException.class);
	}

}
