package net.komunikator.server.plugin.events;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 17.06.13
 * Time: 13:46
 * To change this template use File | Settings | File Templates.
 */
abstract public class Event {
    boolean canceled = false;

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
