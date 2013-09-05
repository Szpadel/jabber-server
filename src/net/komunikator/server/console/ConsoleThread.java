package net.komunikator.server.console;

import net.komunikator.server.event.EventDispatcher;
import net.komunikator.server.event.ShutdownEvent;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 26.08.13
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */
public class ConsoleThread extends Thread {
    Scanner s = new Scanner(System.in);
    PrintStream out = System.out;

    EventDispatcher eventDispatcher = EventDispatcher.getInstance();

    private void printHelp() {
        out.println("Available commands:");
        out.println("help       - prints this list, Sherlock");
        out.println("create     - create objects");
        out.println("connection - connections management");
        out.println("quit       - close server");
    }

    private void shutdown() {
        // place for server shutdown procedure
        eventDispatcher.dispatch("server.shutdown", new ShutdownEvent("Shutdown"));
    }

    @Override
    public void run() {
        String line;
        out.println("Welcome in JavaCommunicator debug console");
        while (true) {
            out.print("debug> ");
            line = s.nextLine() + " ";

            if (line.trim().equals("")) {
                // do nothing
            } else if (line.startsWith("help ") || line.startsWith("h ") || line.startsWith("? ")) {
                printHelp();
            } else if (line.startsWith("create ")) {
                line = line.substring("create ".length());
                Command create = new CreateCommand();
                create.runCommand(out, line);
            } else if (line.startsWith("connection ")) {
                line = line.substring("connection ".length());
                Command connection = new ConnectionCommand();
                connection.runCommand(out, line);
            } else if (line.startsWith("quit ")) {
                shutdown();
                break;
            } else {
                out.println("command invalid, type 'help'");
            }
        }
    }
}
