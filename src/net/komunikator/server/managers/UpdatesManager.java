package net.komunikator.server.managers;

import net.komunikator.server.models.Model;

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

    private final Logger logger = Logger.getLogger(UpdatesManager.class.getName());

    private Map<Long, Queue<Model>> pendingUpdates = new HashMap<Long, Queue<Model>>();

    public static UpdatesManager getInstance() {
        return ourInstance;
    }

    private UpdatesManager() {
    }

    public void addUpdated(Model model) {
        for (Map.Entry<Long, Queue<Model>> queueSet : pendingUpdates.entrySet()) {
            if (!queueSet.getValue().contains(model)) {
                queueSet.getValue().add(model);
            }
        }
        logger.finer("Model " + model.getModelName() + " id:" + model.getId() + " was changed");
    }

    public void registerClient(Long clientToken) {
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
