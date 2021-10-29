package com.taltools.logger;

import com.taltools.test.CommonKeyValuePair;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author czy
 */
@Slf4j
public class SimpleLogger {
    private Queue<String> buffer = new ConcurrentLinkedDeque<>();
    private Formatter formatter;

    public SimpleLogger() {
        this(null);
    }

    public SimpleLogger(Formatter formatter) {
        if (formatter != null) {
            this.formatter = formatter;
        } else {
            this.formatter = new SimpleLogFormatter();
        }
    }

    public void log(String message) {
        if (message != null) {
            buffer.offer(formatter.format(message));
            log.debug(message);
        }
    }

    public void log(Object obj) {
        if (obj != null) {
            log(obj.toString());
        } else {
            log("null");
        }
    }

    public void log(String message, Object... args) {
        log(String.format(message, args));
    }

    public <K, V> void log(Map<K, V> map) {
        if (map != null) {
            map.forEach(((k, v) -> log(k + ": " + v)));
        }
    }

    public <K, V> void log(CommonKeyValuePair<K, V> keyValuePair) {
        if (keyValuePair != null) {
            log(keyValuePair.getKey() + ": " + keyValuePair.getValue());
        }
    }

    public <K, V> void log(List<? extends CommonKeyValuePair<K, V>> list) {
        if (list != null) {
            list.forEach(kvCommonKeyValuePair -> log(kvCommonKeyValuePair));
        }
    }

    public Collection<String> getLogs() {
        return buffer;
    }
}
