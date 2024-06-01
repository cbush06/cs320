package org.snhu.cs320.contact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.snhu.cs320.exceptions.ValidationException;

class ContactServiceTest {
	
	@BeforeEach
	void init() {
		ContactService.CONTACT_DATABASE.clear();
	}

	@Test
	void addSuccess() throws ValidationException {
		Contact contact = new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane");
		ContactService.add(contact);
		assertThat(ContactService.CONTACT_DATABASE)
			.containsEntry("12345", contact);
	}
	
	@Test
	void delete() throws ValidationException {
		ContactService.add(new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane"));
		ContactService.delete("12345");
		assertThat(ContactService.CONTACT_DATABASE)
			.doesNotContainKey("12345");
	}
	
	@Test
	void updateSuccess() throws ValidationException {
		ContactService.add(new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane"));
		
		Contact updated = new Contact("12345", "First", "Last", "2229995555", "1234 Loblolly Lane");
		assertThat(ContactService.update("12345", updated)).isTrue();
		
		assertThat(ContactService.CONTACT_DATABASE)
			.extracting("12345")
			.hasFieldOrPropertyWithValue("phone", "2229995555");
	}
	
	@Test
	void updateFail() throws ValidationException {
		ContactService.add(new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane"));
		
		Contact updated = new Contact("12345", "First", "Last", "2229995555", "1234 Loblolly Lane");
		updated.setAddress(null);
		
		assertThatThrownBy(() -> ContactService.update("12345", updated))
			.isInstanceOf(ValidationException.class);
	}
	
}
