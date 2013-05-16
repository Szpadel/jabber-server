package net.komunikator.server.models;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

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
        public void processMessage(Chat chat, Message message) {
            messages.add(message);
        }
    };

    public Conversation(Connection connection, Contact contact) {
        this.connection = connection;
        this.contact = contact;
        messages = new LinkedBlockingQueue<Message>();
        chat = connection.getConnection().getChatManager().createChat(contact.getJid(), messageListener);
    }


}
