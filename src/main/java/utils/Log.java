package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    private static Logger instance;
    private Log() {

    }
    public static Logger getInstance() {
        if(instance == null) instance = LoggerFactory.getLogger(Log.class);
        return instance;
    }

}
