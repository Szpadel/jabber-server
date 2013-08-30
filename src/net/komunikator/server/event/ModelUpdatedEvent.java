package net.komunikator.server.event;

import net.komunikator.server.models.Model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 30.08.13
 * Time: 18:28
 * To change this template use File | Settings | File Templates.
 */
public class ModelUpdatedEvent extends Event {
    private Model model;
    private Date timestamp;

    public ModelUpdatedEvent(Model model, Date timestamp) {
        this.model = model;
        this.timestamp = timestamp;
    }

    public Model getModel() {
        return model;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
