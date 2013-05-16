package net.komunikator.server.models;

import java.util.Date;

/**
 * @author Piotr Rogowski<piotrekrogowski@gmail.com>
 */
public class Message {
    int id;
    Date timestamp;
    Contact contact;
    Connection connection;
    String message;

    public Message(Date timestamp, Contact contact, String message, Connection connection) {
        this.timestamp = timestamp;
        this.contact = contact;
        this.message = message;
        this.connection = connection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
