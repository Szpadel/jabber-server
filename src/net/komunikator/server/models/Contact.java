package net.komunikator.server.models;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 25.03.13
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 */
public class Contact {
    int id;
    Connection connection;
    String name;
    int status;
    String statusDescription;

    public Contact(int id, Connection connection, String name) {
        this.id = id;
        this.connection = connection;
        this.name = name;
    }

    public void updateStatus(int status, String description)
    {
        this.status = status;
        this.statusDescription = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
