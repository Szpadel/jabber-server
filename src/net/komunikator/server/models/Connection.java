package net.komunikator.server.models;

import net.komunikator.server.event.Event;
import net.komunikator.server.event.EventListenerInterface;
import net.komunikator.server.exceptions.NotConnectedException;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * @author Piotr Rogowski<piotrekrogowski@gmail.com>
 */

public class Connection extends Model {


    int id;
    String name;
    String username;
    String password;
    String domain;
    String resource;
    org.jivesoftware.smack.Connection connection;
    Map<String, Chat> openChats;

    public Connection(int id, String name, String username, String domain, String resource, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.domain = domain;
        this.resource = resource;
        this.password = password;

        eventDispatcher.registerListener("server.shutdown", new EventListenerInterface() {
            @Override
            public void receiveEvent(Event event) {
                disconnect();
            }
        });

        openChats = new LinkedHashMap<String, Chat>();
        changed();

    }

    public boolean isConnected() {
        return connection != null && connection.isConnected();
    }

    public org.jivesoftware.smack.Connection getConnection() {
        return connection;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        changed();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        changed();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
        changed();
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
        changed();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        changed();
    }


    public boolean connect() {
        if (connection == null) {
            connection = new XMPPConnection(domain);
        }
        try {
            connection.connect();
            connection.login(username, password, resource);
        } catch (XMPPException e) {
            logger.log(Level.WARNING, "Connection id:" + id + " connecting error:", e);
            return false;
        }
        logger.info("Connection id:" + id + " is now connecting...");
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
            logger.info("Connection id:" + id + " disconnecting...");
        }
    }

    public void setStatus(Presence.Type type, String description) throws NotConnectedException {
        throwIfCotConnected();
        Presence presence = new Presence(Presence.Type.unavailable);
        presence.setType(type);
        presence.setStatus(description);
        connection.sendPacket(presence);
        changed();
    }

    @Override
    public String getModelName() {
        return "Connection";
    }
}
