package net.komunikator.server.models;

import java.util.Date;

/**
 * @author Piotr Rogowski<piotrekrogowski@gmail.com>
 */
public class Message extends Model {
    int id;
    Date timestamp;
    Contact contact;
    Connection connection;
    String message;
    int sendBy;

    public static final int SEND_BY_CONTACT = 0;
    public static final int SEND_BY_ME = 1;

    public Message(Date timestamp, Contact contact, String message, Connection connection, int sendBy) {
        this.timestamp = timestamp;
        this.contact = contact;
        this.message = message;
        this.connection = connection;
        this.sendBy = sendBy;

        changed();
    }

    public Message() {
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

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public int getSendBy() {
        return sendBy;
    }

    public void setSendBy(int sendBy) {
        this.sendBy = sendBy;
    }

    @Override
    public String getModelName() {
        return "Message";
    }
}
