package net.komunikator.server.managers;

import net.komunikator.server.models.Contact;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 01.09.13
 * Time: 01:23
 * To change this template use File | Settings | File Templates.
 */
public class ContactManager {
    private static ContactManager ourInstance = new ContactManager();

    public static ContactManager getInstance() {
        return ourInstance;
    }

    private ContactManager() {
    }

    final private Map<Integer, Contact> contacts = new HashMap<Integer, Contact>();

    private int nextId = 1;

    public int reserveNextId() {
        return nextId++;
    }

    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getId())) {
            return;
        }
        contacts.put(contact.getId(), contact);
    }

    public Contact getContact(int id) {
        return contacts.get(id);
    }

    public Map<Integer, Contact> getContacts() {
        return contacts;
    }
}
