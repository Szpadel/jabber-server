package net.komunikator.shared.network;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 05.09.13
 * Time: 21:06
 * To change this template use File | Settings | File Templates.
 */
public interface ServerInterface {
    // methods for all clients

    public SessionInterface login(String sessionName, String password, ClientCallbackInterface clientCallback);

}
