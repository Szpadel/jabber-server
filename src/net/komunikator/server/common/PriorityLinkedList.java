package net.komunikator.server.common;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 30.08.13
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
public class PriorityLinkedList<E> {

    public static final int HIGHEST_PRIORITY = 0;
    public static final int HIGH_PRIORITY = 1;
    public static final int NORMAL_PRIORITY = 2;
    public static final int LOW_PRIORITY = 3;
    public static final int LOWEST_PRIORITY = 4;

    final private List<List<E>> lists = new ArrayList<List<E>>(5);

    private List<E> prioritizedList;

    private int size = 0;

    public PriorityLinkedList() {
        for (int a = 0; a < 5; a++) {
            lists.add(new LinkedList<E>());
        }
    }

    private void checkPriority(int priority) {
        if (priority > 4 || priority < 0) {
            throw new InvalidParameterException("Invalid priority");
        }
    }

    public void add(E object, int priority) {
        checkPriority(priority);
        for (List<E> list : lists) {
            if (list.contains(object)) {
                throw new InvalidParameterException("Object already in list");
            }
        }
        lists.get(priority).add(object);
        size++;
        prioritizedList = null;
    }

    public void remove(E object) {
        for (List<E> list : lists) {
            if (list.remove(object)) {
                size--;
                prioritizedList = null; // invalidate cache
                return;
            }
        }
    }

    final public List<E> toList() {
        if (prioritizedList == null) {
            prioritizedList = new ArrayList<E>();
            for (List<E> list : lists) {
                prioritizedList.addAll(list);
            }
        }
        return prioritizedList;
    }
}
