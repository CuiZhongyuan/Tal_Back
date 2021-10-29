package com.taltools.script.runpackage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author czy
 */
public class StepResponseData {
    private Map<String, Object> responseData;

    public StepResponseData() {
        responseData = new ConcurrentHashMap<>();
    }

    public void addResponseData(String name, Object value) {
        if (name != null && value != null) {
            responseData.put(name, value);
        }
    }

    public Object getResponseData(String name) {
        if (name != null) {
            return responseData.get(name);
        }
        return null;
    }

    public Map<String, Object> getRawData() {
        return responseData;
    }
}
