package net.komunikator.server.event;

import net.komunikator.server.common.PriorityLinkedList;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 30.08.13
 * Time: 16:04
 * To change this template use File | Settings | File Templates.
 */
public class EventDispatcher {
    private static EventDispatcher ourInstance = new EventDispatcher();

    public static EventDispatcher getInstance() {
        return ourInstance;
    }

    final private Map<String, PriorityLinkedList<EventListenerInterface>> listeners = new HashMap<String,
            PriorityLinkedList<EventListenerInterface>>();

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    private EventDispatcher() {
    }

    public void registerListener(String eventName, EventListenerInterface eventListener) {
        registerListener(eventName, eventListener, PriorityLinkedList.NORMAL_PRIORITY);
    }

    public void registerListener(String eventName, EventListenerInterface eventListener, int priotity) {
        PriorityLinkedList<EventListenerInterface> list;
        if ((list = listeners.get(eventName)) == null) {
            listeners.put(eventName, list = new PriorityLinkedList<EventListenerInterface>());
        }
        list.add(eventListener, priotity);
        logger.fine("Registered event listener to event " + eventName);
    }

    public void unregisterListener(String eventName, EventListenerInterface eventListener) {
        PriorityLinkedList<EventListenerInterface> list;
        list = listeners.get(eventName);
        if (list == null) {
            return;
        }
        list.remove(eventListener);
        logger.fine("Unregistered event listener from event " + eventName);
    }

    public void dispatch(String eventName, Event event) {
        logger.fine("Dispatching event " + eventName);
        PriorityLinkedList<EventListenerInterface> list = listeners.get(eventName);
        if (list == null) {
            return;
        }
        for (EventListenerInterface eventListener : list.toList()) {
            logger.finest("Dispatching event to " + eventListener.getClass().getName());
            if (event.isCanceled()) {
                logger.finer("Event " + event.getClass().getName() + "canceled, breaking propagation!");
                break;
            }
            eventListener.receiveEvent(event);
        }
    }
}
