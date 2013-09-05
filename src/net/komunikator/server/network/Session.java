package net.komunikator.server.network;

import de.root1.simon.SimonUnreferenced;
import de.root1.simon.annotation.SimonRemote;
import net.komunikator.shared.network.SessionInterface;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 05.09.13
 * Time: 21:40
 * To change this template use File | Settings | File Templates.
 */
@SimonRemote(value = {SessionInterface.class})
public class Session implements SessionInterface, SimonUnreferenced, Serializable {
    private long sessionId;
    private String sessionName;
    private Server server;

    public Session(long sessionId, String sessionName, Server server) {
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.server = server;
    }

    @Override
    public long getSessionId() {
        return sessionId;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    @Override
    public void unreferenced() {

    }
}
