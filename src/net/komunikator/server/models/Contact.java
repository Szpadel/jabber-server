package net.komunikator.server.models;

/**
 * @author Piotr Rogowski<piotrekrogowski@gmail.com>
 */
public class Contact extends Model {
    int id;
    Connection connection;
    String name;
    int status;
    String jid;
    String statusDescription;
    Conversation conversation;

    public Contact(int id, Connection connection, String name) {
        this.id = id;
        this.connection = connection;
        this.name = name;
        changed();
    }

    public void updateStatus(int status, String description) {
        this.status = status;
        this.statusDescription = description;
        changed();
    }

    public int getId() {
        return id;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
        changed();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        changed();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        changed();
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
        changed();
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
        changed();
    }

    public Conversation getConversation() {
        if (conversation == null) {
            conversation = new Conversation(connection, this);
        }
        return conversation;
    }

    @Override
    public String getModelName() {
        return "Contact";
    }
}
