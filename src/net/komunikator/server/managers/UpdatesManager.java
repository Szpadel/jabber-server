package net.komunikator.server.managers;

import net.komunikator.server.common.SharedData;
import net.komunikator.server.event.*;
import net.komunikator.server.models.Connection;
import net.komunikator.server.models.Contact;
import net.komunikator.server.models.Model;
import net.komunikator.server.network.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 22.08.13
 * Time: 00:12
 * To change this template use File | Settings | File Templates.
 */
public class UpdatesManager {
    private static UpdatesManager ourInstance = new UpdatesManager();

    private Map<Long, Queue<Model>> pendingUpdates = new HashMap<Long, Queue<Model>>();

    public static UpdatesManager getInstance() {
        return ourInstance;
    }

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    private EventListenerInterface modelCreatedListener = new EventListenerInterface() {
        @Override
        public void receiveEvent(Event event) {
            ModelCreatedEvent modelCreatedEvent = (ModelCreatedEvent) event;
            addUpdated(modelCreatedEvent.getModel());
        }
    };

    private EventListenerInterface modelUpdatedListener = new EventListenerInterface() {
        @Override
        public void receiveEvent(Event event) {
            ModelUpdatedEvent modelUpdatedEvent = (ModelUpdatedEvent) event;
            addUpdated(modelUpdatedEvent.getModel());
        }
    };

    private UpdatesManager() {
        EventDispatcher eventDispatcher = EventDispatcher.getInstance();
        eventDispatcher.registerListener("model.created", modelCreatedListener);
        eventDispatcher.registerListener("model.updated", modelUpdatedListener);
    }

    protected void addUpdated(Model model) {
        for (Map.Entry<Long, Queue<Model>> queueSet : pendingUpdates.entrySet()) {
            if (!queueSet.getValue().contains(model)) {
                Session session = SharedData.serverObject.getSession(queueSet.getKey());
                if(session == null) {
                    continue;
                }
                queueSet.getValue().add(model);
                addModel(model, session);
            }
        }
        logger.finer("Model " + model.getModelName() + " id:" + model.getId() + " was changed");
    }

    private void addModel(Model model, Session session) {
        if(model instanceof Connection) {
            Connection connection = (Connection) model;
            session.addConnection(connection);

        }else if(model instanceof Contact) {
            Contact contact = (Contact) model;
            session.addContact(contact);
        }
    }

    public void registerClient(Session session) {
        long clientToken = session.getSessionId();
        if (pendingUpdates.containsKey(clientToken)) {
            logger.warning("Client id " + clientToken + " already registered, aborting!");
            return;
        }
        pendingUpdates.put(clientToken, new LinkedTransferQueue<Model>());
        logger.info("Client id " + clientToken + " registered");
    }

    public Queue<Model> getUpdatesQueue(Long clientToken) {
        return pendingUpdates.get(clientToken);
    }
}
