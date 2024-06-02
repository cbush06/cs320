package org.snhu.cs320.contact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.snhu.cs320.exceptions.ValidationException;

class ContactTest {

	@Test
	void testSuccessfulCreation() throws ValidationException {
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
		"'',First,Last,5553334444,1234 Loblolly Lane,id must not be blank", // Blank ID
		",First,Last,5553334444,1234 Loblolly Lane,id must not be null", // Null ID
		"12345678901,First,Last,5553334444,1234 Loblolly Lane,id must be at least 1 and no greater than 10 characters in length", // Too Long ID
		"12345,'',Last,5553334444,1234 Loblolly Lane,firstName must not be blank", // Blank First Name
		"12345,,Last,5553334444,1234 Loblolly Lane,firstName must not be null", // Null First Name
		"12345,FirstFirstF,Last,5553334444,1234 Loblolly Lane,firstName must be at least 1 and no greater than 10 characters in length", // Too Long First Name
		"12345,First,'',5553334444,1234 Loblolly Lane,lastName must not be blank", // Blank Last Name
		"12345,First,,5553334444,1234 Loblolly Lane,lastName must not be null", // Null Last Name
		"12345,First,LastLastLas,5553334444,1234 Loblolly Lane,lastName must be at least 1 and no greater than 10 characters in length", // Too Long Last Name
		"12345,First,Last,'',1234 Loblolly Lane,phone must not be blank", // Blank Phone
		"12345,First,Last,,1234 Loblolly Lane,phone must not be null", // Null Phone
		"12345,First,Last,55533344449,1234 Loblolly Lane,phone must be at least 10 and no greater than 10 characters in length", // Too Long Phone
		"12345,First,Last,555333444A,1234 Loblolly Lane,phone must only contain digits", // Phone with Letters
		"12345,First,Last,555333-444,1234 Loblolly Lane,phone must only contain digits", // Phone with Punctuation
		"12345,First,Last,555333 444,1234 Loblolly Lane,phone must only contain digits", // Phone with Spaces
		"12345,First,Last,5553334444,'',address must not be blank", // Blank Address
		"12345,First,Last,5553334444,,address must not be null", // Null Address
		"12345,First,Last,5553334444,1234 Loblolly Lane 1234 Lobloll,address must be at least 1 and no greater than 30 characters in length", // Too Long Address
	})
	void testFailedCreation(String id, String firstName, String lastName, String phone, String address, String message) {
		assertThatThrownBy(() -> new Contact(id, firstName, lastName, phone, address))
			.isInstanceOf(ValidationException.class)
			.hasMessage(message);
	}
	
    @Test
	void testSuccessfulSetters() throws ValidationException {
		Contact contact = new Contact("1", "First", "Last", "5553334444", "1234 Loblolly Lane");
        contact.setFirstName("Bob");
        contact.setLastName("Ross");
        contact.setPhone("3339991111");
        contact.setAddress("1234 Painter Lane");

        assertThat(contact)
            .hasFieldOrPropertyWithValue("firstName", "Bob")
            .hasFieldOrPropertyWithValue("lastName", "Ross")
            .hasFieldOrPropertyWithValue("phone", "3339991111")
            .hasFieldOrPropertyWithValue("address", "1234 Painter Lane");
    }

    @ParameterizedTest
    @CsvSource({
        "'',firstName must not be blank", // Blank First Name
		",firstName must not be null", // Null First Name
		"FirstFirstF,firstName must be at least 1 and no greater than 10 characters in length", // Too Long First Name
    })
    void testSettingFirstName(String firstName, String message) throws ValidationException {
        Contact contact = new Contact("1", "First", "Last", "5553334444", "1234 Loblolly Lane");
        assertThatThrownBy(() -> contact.setFirstName(firstName))
            .isInstanceOf(ValidationException.class)
            .hasMessage(message);
    }

    @ParameterizedTest
    @CsvSource({
        "'',lastName must not be blank", // Blank Last Name
		",lastName must not be null", // Null Last Name
		"LastLastLas,lastName must be at least 1 and no greater than 10 characters in length", // Too Long Last Name
    })
    void testSettingLastName(String lastName, String message) throws ValidationException {
        Contact contact = new Contact("1", "First", "Last", "5553334444", "1234 Loblolly Lane");
        assertThatThrownBy(() -> contact.setLastName(lastName))
            .isInstanceOf(ValidationException.class)
            .hasMessage(message);
    }

    @ParameterizedTest
    @CsvSource({
        "'',phone must not be blank", // Blank Phone
		",phone must not be null", // Null Phone
		"55533344449,phone must be at least 10 and no greater than 10 characters in length", // Too Long Phone
		"555333444A,phone must only contain digits", // Phone with Letters
		"555333-444,phone must only contain digits", // Phone with Punctuation
		"555333 444,phone must only contain digits", // Phone with Spaces
    })
    void testSettingPhone(String phone, String message) throws ValidationException {
        Contact contact = new Contact("1", "First", "Last", "5553334444", "1234 Loblolly Lane");
        assertThatThrownBy(() -> contact.setPhone(phone))
            .isInstanceOf(ValidationException.class)
            .hasMessage(message);
    }

    @ParameterizedTest
    @CsvSource({
		"'',address must not be blank", // Blank Address
		",address must not be null", // Null Address
		"1234 Loblolly Lane 1234 Lobloll,address must be at least 1 and no greater than 30 characters in length", // Too Long Address
    })
    void testSettingAddress(String address, String message) throws ValidationException {
        Contact contact = new Contact("1", "First", "Last", "5553334444", "1234 Loblolly Lane");
        assertThatThrownBy(() -> contact.setAddress(address))
            .isInstanceOf(ValidationException.class)
            .hasMessage(message);
    }

}
