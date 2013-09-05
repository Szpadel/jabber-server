package net.komunikator.server.network;

import de.root1.simon.Registry;
import de.root1.simon.Simon;
import de.root1.simon.exceptions.NameBindingException;
import net.komunikator.server.event.Event;
import net.komunikator.server.event.EventDispatcher;
import net.komunikator.server.event.EventListenerInterface;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 05.09.13
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public class NetworkListener {

    int port = 13337;
    Server server;
    Logger logger = Logger.getLogger(this.getClass().getName());
    Registry registry;


    public NetworkListener() {
        EventListenerInterface shutdownEventListener = new EventListenerInterface() {
            @Override
            public void receiveEvent(Event event) {
                shutdown();
            }
        };

        EventDispatcher eventDispatcher = EventDispatcher.getInstance();
        eventDispatcher.registerListener("server.shutdown", shutdownEventListener);
    }

    public void initialize() throws IOException, NameBindingException {
        server = new Server();
        registry = Simon.createRegistry(port);
        registry.start();
        registry.bind("server", server);
        logger.info("Network up and running on port " + port);
    }

    public void shutdown() {
        if (registry != null) {
            registry.unbind("server");
            registry.stop();
            logger.info("Network shut down");
        }
        registry = null;
    }
}
