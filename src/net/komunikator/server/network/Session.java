package net.komunikator.server.network;

import de.root1.simon.SimonUnreferenced;
import de.root1.simon.annotation.SimonRemote;
import net.komunikator.server.exceptions.ConnectionException;
import net.komunikator.server.managers.ConnectionManager;
import net.komunikator.server.managers.ContactManager;
import net.komunikator.server.models.Connection;
import net.komunikator.server.models.Contact;
import net.komunikator.server.models.Message;
import net.komunikator.shared.network.ClientCallbackInterface;
import net.komunikator.shared.network.SessionInterface;

import java.io.Serializable;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 05.09.13
 * Time: 21:40
 * To change this template use File | Settings | File Templates.
 */
@SimonRemote(value = {SessionInterface.class})
public class Session implements SessionInterface, SimonUnreferenced, Serializable {
    private long sessionId;
    private String sessionName;
    private Server server;
    ClientCallbackInterface client;

    public Session(long sessionId, String sessionName, Server server, ClientCallbackInterface client) {
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.server = server;
        this.client = client;
    }

    @Override
    public long getSessionId() {
        return sessionId;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    public ClientCallbackInterface getClient() {
        return client;
    }

    @Override
    public boolean isValid() {
        return server.getSession(sessionId) != null;
    }

    @Override
    public void unreferenced() {
        server.removeSession(this);
    }

    public void addConnection(Connection connection) {
        client.addConnection(
                connection.getId(),
                connection.getUsername(),
                connection.getDomain(),
                connection.getResource()
        );
    }

    public void addContact(Contact contact) {
        client.addContact(
                contact.getId(),
                contact.getConnection().getId(),
                contact.getName(),
                contact.getStatus(),
                contact.getJid(),
                contact.getStatusDescription()
        );
    }

    public void addMessage(Message message) {
        client.addMessage(message.getId(),
                message.getContact().getId(),
                message.getMessage(),
                message.getSendBy());
    }

    public void synchronize() {
        for (Map.Entry<Integer, Connection> entry : ConnectionManager.getInstance().getConnections().entrySet()) {
            addConnection(entry.getValue());
        }
        for (Map.Entry<Integer, Contact> entry : ContactManager.getInstance().getContacts().entrySet()) {
            addContact(entry.getValue());
        }
    }

    @Override
    public void sendMessage(int contactId, String message) {
        Contact contact = ContactManager.getInstance().getContact(contactId);
        try {
            contact.getConversation().sendMessage(message);
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
    }
}
