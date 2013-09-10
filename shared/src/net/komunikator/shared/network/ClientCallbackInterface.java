package net.komunikator.shared.network;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 05.09.13
 * Time: 21:06
 * To change this template use File | Settings | File Templates.
 */
public interface ClientCallbackInterface {
    // methods for client

    public void toast(String message);

    public void addContact(int id, int connection, String name, int status, String jid, String statusDescription);

    public void addConnection(int id, String username, String domain, String resource);
}
