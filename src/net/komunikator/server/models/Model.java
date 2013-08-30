package net.komunikator.server.models;

import net.komunikator.server.event.EventDispatcher;
import net.komunikator.server.event.ModelCreatedEvent;
import net.komunikator.server.event.ModelUpdatedEvent;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 22.08.13
 * Time: 00:17
 * To change this template use File | Settings | File Templates.
 */
abstract public class Model {
    protected Date lastUpdated = new Date();
    protected EventDispatcher eventDispatcher = EventDispatcher.getInstance();
    protected Logger logger = Logger.getLogger(this.getClass().getName());

    abstract public String getModelName();

    abstract public int getId();

    protected void changed() {
        lastUpdated = new Date();
        ModelUpdatedEvent event = new ModelUpdatedEvent(this, (Date) lastUpdated.clone());

        eventDispatcher.dispatch("model." + getModelName().toLowerCase() + ".updated", event);
        event.reset();
        eventDispatcher.dispatch("model.updated", event);
    }

    protected void created() {
        lastUpdated = new Date();
        ModelCreatedEvent event = new ModelCreatedEvent(this, (Date) lastUpdated.clone());

        eventDispatcher.dispatch("model." + getModelName().toLowerCase() + ".created", event);
        event.reset();
        eventDispatcher.dispatch("model.created", event);
    }
}
