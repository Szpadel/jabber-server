import net.komunikator.server.console.ConsoleThread;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    public static final Logger LOGGER = Logger.getLogger("");
    private ConsoleThread consoleThread = new ConsoleThread();

    public static void main(String[] args) {
        Main main = new Main();
        main.realMain(args);
    }

    private void realMain(String[] args) {
        LOGGER.setLevel(Level.ALL);
        LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.ALL);
        for (Handler handler : LOGGER.getHandlers()) {
            handler.setLevel(Level.ALL);
        }
        LOGGER.info("Server started");
        consoleThread.start();
    }


}
