package net.komunikator.server.models;

import net.komunikator.server.exceptions.NotConnectedException;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Piotr Rogowski<piotrekrogowski@gmail.com>
 */

public class Connection {


    int id;
    String name;
    String username;
    String password;
    String domain;
    String resource;
    org.jivesoftware.smack.Connection connection;
    Map<String, Chat> openChats;

    public Connection(int id, String name, String username, String domain, String resource) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.domain = domain;
        this.resource = resource;

        openChats = new LinkedHashMap<String, Chat>();


    }

    public org.jivesoftware.smack.Connection getConnection() {
        return connection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean connect() {
        if (connection == null) {
            connection = new XMPPConnection(domain);
        }
        try {
            connection.connect();
            connection.login(username, password, resource);
        } catch (XMPPException e) {
            return false;
        }
        return true;
    }

    protected void throwIfCotConnected() throws NotConnectedException {
        if (connection == null || !connection.isConnected()) {
            throw new NotConnectedException();
        }
    }

    public void disconnect() {
        if (connection != null) {
            connection.disconnect();
        }
    }

    public void setStatus(Presence.Type type, String description) throws NotConnectedException {
        throwIfCotConnected();
        Presence presence = new Presence(Presence.Type.unavailable);
        presence.setType(type);
        presence.setStatus(description);
        connection.sendPacket(presence);
    }
}
