package net.komunikator.server.models;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Piotr Rogowski<piotrekrogowski@gmail.com>
 */
public class Conversation {
    Connection connection;
    Contact contact;
    Chat chat;
    Queue<Message> messages;
    MessageListener messageListener = new MessageListener() {
        @Override
        public void processMessage(Chat chat, org.jivesoftware.smack.packet.Message message) {
            Message newMess = new Message(new Date(), contact, message.getBody(), connection);
            messages.add(newMess);
        }
    };

    public Conversation(Connection connection, Contact contact) {
        this.connection = connection;
        this.contact = contact;
        messages = new LinkedBlockingQueue<Message>();
        chat = connection.getConnection().getChatManager().createChat(contact.getJid(), messageListener);
    }

    public Message getNewMessage() {
        return messages.poll();
    }

}
