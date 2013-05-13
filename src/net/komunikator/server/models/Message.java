package net.komunikator.server.models;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 25.03.13
 * Time: 17:03
 * To change this template use File | Settings | File Templates.
 */
public class Message {
    int id;
    Date timestamp;
    Contact contact;
    String message;

    public Message(int id, Date timestamp, Contact contact, String message) {
        this.id = id;
        this.timestamp = timestamp;
        this.contact = contact;
        this.message = message;
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
