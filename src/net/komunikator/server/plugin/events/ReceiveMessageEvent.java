package net.komunikator.server.plugin.events;

import net.komunikator.server.models.Message;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 17.06.13
 * Time: 13:46
 * To change this template use File | Settings | File Templates.
 */
public class ReceiveMessageEvent {
    Message message;

    public Message getMessage() {
        return message;
    }


}
