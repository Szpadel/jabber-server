package net.komunikator.server.console;

import net.komunikator.server.managers.ConnectionManager;
import net.komunikator.server.managers.ContactManager;
import net.komunikator.server.models.Connection;
import net.komunikator.server.models.Contact;

import java.io.PrintStream;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 26.08.13
 * Time: 23:36
 * To change this template use File | Settings | File Templates.
 */
public class CreateCommand extends Command {

    /**
     * Simple random string generator
     *
     * @param len
     * @return
     */
    private String genRandomString(int len) {
        String chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        String gen = "";
        Random random = new Random();
        for (int a = 0; a < len; a++) {
            gen += chars.charAt(random.nextInt(chars.length()));
        }
        return gen;
    }

    @Override
    public void runCommand(PrintStream out, String line) {
        if (line.startsWith("connection ")) {
            line = line.substring("connection ".length());
            String[] params = line.split(" ", 2);

            if (params.length != 2) {
                out.println("insufficient number of params");
                return;
            }

            String name = params[0];

            String[] jidParams = params[1].trim().split("@", 2);
            if (jidParams.length != 2) {
                out.println("Invalid jid format");
                return;
            }

            String username = jidParams[0];

            jidParams = jidParams[1].split("/", 2);

            String resource;
            if (jidParams.length != 2) {
                resource = "JavaCommunicator." + genRandomString(5);
            } else {
                resource = jidParams[1];
            }

            String domain = jidParams[0];

            String password = System.console().readPassword("password:").toString();

            ConnectionManager connectionManager = ConnectionManager.getInstance();
            Connection connection = new Connection(connectionManager.reserveNextId(), name, username, domain, resource, password);
            connectionManager.addConnection(connection);

            out.println("OK");
        } else if (line.startsWith("contact ")) {
            line.substring("contact ".length());
            String params[] = line.trim().split(" ");

            if (params.length != 3) {
                out.println("Invalid parameters number");
                return;
            }

            int connId;
            try {
                connId = Integer.parseInt(params[0]);
            } catch (NumberFormatException e) {
                out.println("invalid id");
                return;
            }
            String name = params[1];
            String jid = params[2];

            ConnectionManager connectionManager = ConnectionManager.getInstance();
            ContactManager contactManager = ContactManager.getInstance();

            Connection connection = connectionManager.getConnection(connId);

            if (connection == null) {
                out.println("connection doesn't exists");
            }

            Contact contact = new Contact(contactManager.reserveNextId(), connection, name, jid);
            contactManager.addContact(contact);
        } else {
            out.println("syntax: create ");
            out.println("               connection [name] [username@domain/resource]");
            out.println("               contact [connection id] [name] [jid]");
        }
    }
}
