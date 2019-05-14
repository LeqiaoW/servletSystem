package logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleLogger {

    public static Logger logger = LogManager.getLogger(SimpleLogger.class.getName());

}
