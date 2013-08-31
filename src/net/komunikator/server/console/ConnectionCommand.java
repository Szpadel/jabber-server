package net.komunikator.server.console;

import net.komunikator.server.managers.ConnectionManager;
import net.komunikator.server.models.Connection;

import java.io.PrintStream;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 26.08.13
 * Time: 23:53
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionCommand extends Command {

    private void printHelp(PrintStream out) {
        out.println("connection ");
        out.println("           list            - lists connections");
        out.println("           connect [id]    - connect connection");
        out.println("           disconnect [id] - disconnect connection");
    }

    private void listConnections(PrintStream out) {
        out.println("Connections list:");
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        for (Map.Entry<Integer, Connection> connectionEntry : connectionManager.getConnections().entrySet()) {
            Connection connection = connectionEntry.getValue();
            out.println("id:" + connectionEntry.getKey() + " name:" + connection.getName() +
                    " username:" + connection.getUsername() + " domain:" + connection.getDomain() +
                    " resource:" + connection.getResource() + " connected:" +
                    (connection.isConnected() ? "yes" : "no"));
        }
    }

    @Override
    public void runCommand(PrintStream out, String command) {
        if (command.startsWith("list ")) {
            listConnections(out);
        } else if (command.startsWith("connect ")) {
            try {
                int id = Integer.parseInt(command.substring("connect ".length()).trim());
                out.println("connecting...");
                if (ConnectionManager.getInstance().getConnection(id).connect()) {
                    out.println("OK");
                } else {
                    out.println("ERROR");
                }
            } catch (NumberFormatException e) {
                out.println("invalid id");
            }
        } else if (command.startsWith("disconnect ")) {
            try {
                int id = Integer.parseInt(command.substring("disconnect ".length()).trim());
                ConnectionManager.getInstance().getConnection(id).disconnect();
                out.println("OK");
            } catch (NumberFormatException e) {
                out.println("invalid id");
            }
        } else {
            printHelp(out);
        }
    }
}
