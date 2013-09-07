package net.komunikator.server.console;

import net.komunikator.server.common.Config;
import net.komunikator.server.event.Event;
import net.komunikator.server.event.EventDispatcher;
import net.komunikator.server.event.EventListenerInterface;
import net.komunikator.server.event.ShutdownEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 26.08.13
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */
public class ConsoleThread extends Thread {
    PrintStream out = System.out;

    Logger logger = Logger.getLogger(this.getClass().getName());

    EventDispatcher eventDispatcher = EventDispatcher.getInstance();

    boolean isShutdown = false;

    public ConsoleThread() {
        EventListenerInterface shutdownEventListener = new EventListenerInterface() {
            @Override
            public void receiveEvent(Event event) {
                shutdown();
            }
        };
        eventDispatcher.registerListener("server.shutdown", shutdownEventListener);
    }

    private void printHelp() {
        out.println("Available commands:");
        out.println("help       - prints this list, Sherlock");
        out.println("create     - create objects");
        out.println("connection - connections management");
        out.println("password   - set password");
        out.println("quit       - close server");
    }

    private void shutdown() {
        logger.info("Console shut down");
        isShutdown = true;
    }

    private void callShutdown() {
        // place for server shutdown procedure
        eventDispatcher.dispatch("server.shutdown", new ShutdownEvent("Shutdown"));
    }

    @Override
    public void run() {
        try {
            String line;
            out.println("Welcome in JavaCommunicator debug console");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (!isShutdown) {
                out.print("debug> ");
                while (!br.ready()) {
                    sleep(100);
                }
                line = br.readLine() + " ";

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
                    callShutdown();
                } else if (line.startsWith("password ")) {
                    char[] password = System.console().readPassword("password:");
                    Config.password = String.valueOf(password);
                    out.println("Success");
                } else {
                    out.println("command invalid, type 'help'");
                }
            }
        } catch (InterruptedException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }
}
