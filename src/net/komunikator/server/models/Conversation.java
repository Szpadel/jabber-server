package net.komunikator.server.models;

import net.komunikator.server.exceptions.ConnectionException;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPException;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Piotr Rogowski<piotrekrogowski@gmail.com>
 */
public class Conversation extends Model {
    Connection connection;
    Contact contact;
    Chat chat;
    Queue<Message> messages;
    List<Message> history;
    MessageListener messageListener = new MessageListener() {
        @Override
        public void processMessage(Chat chat, org.jivesoftware.smack.packet.Message message) {
            Message newMess = new Message(new Date(), contact, message.getBody(), connection, Message.SEND_BY_CONTACT);
            messages.add(newMess);
            history.add(newMess);
        }
    };

    public Conversation(Connection connection, Contact contact) {
        this.connection = connection;
        this.contact = contact;
        messages = new LinkedBlockingQueue<Message>();
        chat = connection.getConnection().getChatManager().createChat(contact.getJid(), messageListener);
        history = new LinkedList<Message>();

        changed();
    }

    public Message getNewMessage() {
        return messages.poll();
    }

    public void sendMessage(String msg) throws ConnectionException {
        try {
            chat.sendMessage(msg);
            Message mess = new Message(new Date(), contact, msg, connection, Message.SEND_BY_ME);
            history.add(mess);
            changed();

        } catch (XMPPException e) {
            throw new ConnectionException("Can't send message", e);
        }
    }

    @Override
    public String getModelName() {
        return "Conversation";
    }

    @Override
    public int getId() {
        return 0; // Conversation isn't real model
    }
}
