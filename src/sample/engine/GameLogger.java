package sample.engine;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * handles the game logging.
 * @author Yash Suresh-modified
 */
public class GameLogger extends Logger
{
    private static final Logger m_logger = Logger.getLogger("GameLogger");
    private final DateFormat m_dateFormat =
            new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final Calendar m_calendar = Calendar.getInstance();


    /**
     * constructor
     * @throws IOException if error loading file
     */
    public GameLogger() throws IOException
    {
        super("GameLogger", null);

        File directory = new File(System.getProperty("user.dir") + "/" + "logs");
        directory.mkdirs();

        FileHandler fh = new FileHandler(directory + "/" + StartMeUp.getGAME_NAME() + ".log");
        m_logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }

    /**
     *
     * @param message a message
     * @return message with the date
     */
    private String createFormattedMessage(String message)
    {
        return m_dateFormat.format(m_calendar.getTime()) + " -- " + message;
    }

    /**
     * creates a message
     * @param message a custom formatted message
     */
    public void info(String message)
    {
        m_logger.info(createFormattedMessage(message));
    }

    /**
     * produces a warning
     * @param message the warning message
     */
    public void warning(String message)
    {
        m_logger.warning(createFormattedMessage(message));
    }

    /**
     * creates a formatted message
     * @param message the formatted message
     */
    public void severe(String message)
    {
        m_logger.severe(createFormattedMessage(message));
    }
}