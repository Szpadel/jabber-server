import de.root1.simon.exceptions.NameBindingException;
import net.komunikator.server.console.ConsoleThread;
import net.komunikator.server.event.EventDispatcher;
import net.komunikator.server.event.ShutdownEvent;
import net.komunikator.server.managers.UpdatesManager;
import net.komunikator.server.network.NetworkListener;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    public static final Logger LOGGER = Logger.getLogger("");
    private ConsoleThread consoleThread = new ConsoleThread();

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.realMain(args);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unknown error", e);
            EventDispatcher.getInstance().dispatch("server.shutdown", new ShutdownEvent("Server crash"));
        }
    }

    private void realMain(String[] args) throws IOException, NameBindingException {
        LOGGER.setLevel(Level.ALL);
        LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.ALL);
        for (Handler handler : LOGGER.getHandlers()) {
            handler.setLevel(Level.ALL);
        }
        LOGGER.info("Server started");

        UpdatesManager.getInstance(); // initialize
        NetworkListener networkListener = new NetworkListener();
        networkListener.initialize();

        consoleThread.start();
    }


}
