package net.komunikator.server.managers;

import net.komunikator.server.models.Connection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 26.08.13
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionManager {
    private static ConnectionManager ourInstance = new ConnectionManager();

    public static ConnectionManager getInstance() {
        return ourInstance;
    }

    private ConnectionManager() {
    }

    final private Map<Integer, Connection> connections = new HashMap<Integer, Connection>(1);
    int nextId = 1;

    public void addConnection(Connection connection) {
        connections.put(connection.getId(), connection);
    }

    public int reserveNextId() {
        return nextId++;
    }

    public Connection getConnection(int id) {
        return connections.get(id);
    }

    public Map<Integer, Connection> getConnections() {
        return connections;
    }
}
