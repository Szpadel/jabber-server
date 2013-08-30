package net.komunikator.server.models;

import net.komunikator.server.managers.UpdatesManager;

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
    protected UpdatesManager updatesManager = UpdatesManager.getInstance();
    protected Logger logger = Logger.getLogger(this.getClass().getName());


    abstract public String getModelName();

    abstract public int getId();

    protected void changed() {
        lastUpdated = new Date();
        updatesManager.addUpdated(this);
    }
}
