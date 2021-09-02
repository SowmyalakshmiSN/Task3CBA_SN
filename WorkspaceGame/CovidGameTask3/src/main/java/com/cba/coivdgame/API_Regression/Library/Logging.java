package com.cba.coivdgame.API_Regression.Library;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to handle logging
 */
public class Logging {

    private static Logger doLog;

    public synchronized static void info (String message) {
        doLog = LoggerFactory.getLogger (getCallerClass ());
        doLog.info (message);
    }

    public synchronized static void warn (String message) {
        doLog = LoggerFactory.getLogger (getCallerClass ());
        doLog.warn (message);
    }

    public synchronized static void debug (String message) {
        doLog = LoggerFactory.getLogger (getCallerClass ());
        doLog.debug (message);
    }

    public synchronized static void error (String message) {
        doLog = LoggerFactory.getLogger (getCallerClass ());
        doLog.error (message);
    }

    private synchronized static String getCallerClass () {
        for (StackTraceElement elem : Thread.currentThread ().getStackTrace ()) {
            if (elem.getClassName () != Logging.class.getName () && elem.getClassName () != Thread.class.getName ()) {
                return StringUtils.join (new String[] {
                        elem.getClassName (),
                        String.format ("%1$s(%2$d)", elem.getMethodName (), elem.getLineNumber ()) }, ".");
            }
        }
        return null;
    }
}
