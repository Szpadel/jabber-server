package net.komunikator.server.network;

import de.root1.simon.annotation.SimonRemote;
import net.komunikator.server.common.Config;
import net.komunikator.shared.network.ClientCallbackInterface;
import net.komunikator.shared.network.ServerInterface;
import net.komunikator.shared.network.SessionInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 05.09.13
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
@SimonRemote(value = {ServerInterface.class})
public class Server implements ServerInterface {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    long sessionId = 1;

    private List<Session> loggedClients = new LinkedList<Session>();

    @Override
    public SessionInterface login(String sessionName, String password, ClientCallbackInterface clientCallback) {
        logger.info("User authentication. user:" + sessionName);
        if (!password.equals(Config.password)) {
            clientCallback.toast("Authentication failed! Wrong password");
            logger.info("Authentication failed! Wrong password. user:" + sessionName);
            return null;
        }
        Session session = new Session(sessionId, sessionName, this);
        loggedClients.add(session);
        logger.info("User authenticated! user:" + sessionName + " id:" + sessionId);
        sessionId++;
        return session;
    }

    public void removeSession(Session session) {
        logger.info("Removing session id:" + session.getSessionId() + " name:" + session.getSessionName());
        loggedClients.remove(session);
    }
}
