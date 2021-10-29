package com.taltools.logger;

import java.util.Date;

/**
 * @author czy
 */
public class SimpleLogFormatter implements Formatter {
    public static final String DEFAULT_FORMAT = "[%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL] %2$s%n";
    private final Date dat = new Date();

    @Override
    public String format(String message) {
        dat.setTime(System.currentTimeMillis());
        return String.format(DEFAULT_FORMAT, dat, message);
    }
}
