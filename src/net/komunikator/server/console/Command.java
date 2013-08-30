package net.komunikator.server.console;

import java.io.Console;
import java.io.PrintStream;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 26.08.13
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 */
public abstract class Command {
    protected Console console = System.console();

    public abstract void runCommand(PrintStream out, String command);

    public void setConsole(Console console) {
        this.console = console;
    }

}
