package net.komunikator.server.console;

import net.komunikator.server.common.SharedData;
import net.komunikator.server.network.Server;
import net.komunikator.server.network.Session;

import java.io.PrintStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 07.09.13
 * Time: 23:43
 * To change this template use File | Settings | File Templates.
 */
public class ClientCommand extends Command {

    public void printSessionsList(PrintStream out, List<Session> sessions) {
        out.println("Active sessions:");
        for(Session session : sessions) {
            out.println("id:" + session.getSessionId() + " name:" + session.getSessionName());
        }
    }

    @Override
    public void runCommand(PrintStream out, String command) {
        if(command.startsWith("list ")) {
            Server server = SharedData.serverObject;
            printSessionsList(out, server.getLoggedClients());
        }else {
            out.println("syntax: client ");
            out.println("               list");
            out.println("               toast [message]");
        }
    }
}
