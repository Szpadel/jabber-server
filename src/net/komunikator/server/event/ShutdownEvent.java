package net.komunikator.server.event;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 31.08.13
 * Time: 19:49
 * To change this template use File | Settings | File Templates.
 */
public class ShutdownEvent extends Event {
    String reason;

    public ShutdownEvent(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
