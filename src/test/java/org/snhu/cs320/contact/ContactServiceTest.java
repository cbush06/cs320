package org.snhu.cs320.contact;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.snhu.cs320.exceptions.ValidationException;

class ContactServiceTest {
	
	@BeforeEach
	void init() {
		ContactService.getInstance().database.clear();
	}

	@Test
	void addSuccess() throws ValidationException {
		Contact contact = new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane");
		assertThat(ContactService.getInstance().add(contact)).isTrue();
		assertThat(ContactService.getInstance().database).containsEntry("12345", contact);
	}

    @Test
    void addFail() throws ValidationException {
        Contact contact = new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane");
		assertThat(ContactService.getInstance().add(contact)).isTrue();
		assertThat(ContactService.getInstance().add(contact)).isFalse();
    }
	
	@Test
	void delete() throws ValidationException {
		Contact contact = new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane");
		ContactService.getInstance().add(contact);
		assertThat(ContactService.getInstance().database).containsEntry("12345", contact);
		assertThat(ContactService.getInstance().delete("12345")).isTrue();
		assertThat(ContactService.getInstance().database).doesNotContainKey("12345");
	}

    @Test
    void deleteFail() {
        assertThat(ContactService.getInstance().delete("non-existent id")).isFalse();
    }
	
	@Test
	void updateSuccess() throws ValidationException {
		Contact contact = new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane");
		ContactService.getInstance().add(contact);
		assertThat(ContactService.getInstance().database).containsEntry("12345", contact);
		
		Contact modified = new Contact("99999", "Bob", "Willis", "9997773333", "555 Test Lane");
		assertThat(ContactService.getInstance().update("12345", modified)).isTrue();
		assertThat(ContactService.getInstance().database.get("12345"))
			.hasFieldOrPropertyWithValue("firstName", "Bob")
			.hasFieldOrPropertyWithValue("lastName", "Willis")
			.hasFieldOrPropertyWithValue("phone", "9997773333")
			.hasFieldOrPropertyWithValue("address", "555 Test Lane");
	}

    @Test
    void updateFail() throws ValidationException {
        Contact updated = new Contact("12345", "First", "Last", "2229995555", "1234 Loblolly Lane");
        assertThat(ContactService.getInstance().update("12345", updated)).isFalse();
    }
	
}
