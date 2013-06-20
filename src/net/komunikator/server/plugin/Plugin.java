package net.komunikator.server.plugin;

import net.komunikator.server.models.Contact;
import net.komunikator.server.models.Message;
import net.komunikator.server.plugin.events.ReceiveMessageEvent;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 17.06.13
 * Time: 13:34
 * To change this template use File | Settings | File Templates.
 */
abstract public class Plugin {
    abstract String getName();

    abstract String getDescription();

    abstract String getHumanVersion();

    abstract int getVersion();

    public void receiveMessageEvent(ReceiveMessageEvent messageEvent) {
        //empty
    }

    public void cancelMessage(Message message) {

    }

    public void sendMessage(Message message) {

    }

    public void sendMessageEvent(Message message) {
        //empty
    }

    public void contactStatusChangedEvent(Contact contact) {
        //empty
    }
}
