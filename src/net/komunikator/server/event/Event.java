package net.komunikator.server.event;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 30.08.13
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */
public abstract class Event {
    boolean canceled = false;

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }

    /**
     * Resets event to primary state before event was called
     */
    public void reset() {
        canceled = false;
    }
}
