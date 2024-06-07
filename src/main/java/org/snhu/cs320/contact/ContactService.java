package org.snhu.cs320.contact;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.snhu.cs320.exceptions.ValidationException;

public class ContactService {
	
	static Map<String, Contact> CONTACT_DATABASE;
	
	private ContactService() {}

    public static synchronized Map<String, Contact> getInstance() {
        if (CONTACT_DATABASE == null) {
            CONTACT_DATABASE = new ConcurrentHashMap<String, Contact>();
        }
        return CONTACT_DATABASE;
    }
	
	public static boolean add(Contact contact) {
		return getInstance().putIfAbsent(contact.getId(), contact) == null;
	}
	
	public static boolean delete(String id) {
		return getInstance().remove(id) != null;
	}
	
	public static boolean update(String id, Contact updated) throws ValidationException {
		Contact existing = getInstance().get(id);
		
		if (existing == null) return false;
		
		existing.setFirstName(updated.getFirstName());
		existing.setLastName(updated.getLastName());
		existing.setPhone(updated.getPhone());
		existing.setAddress(updated.getAddress());
		
		return true;
	}
	
}
