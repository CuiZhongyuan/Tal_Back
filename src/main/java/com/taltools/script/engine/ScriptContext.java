package com.taltools.script.engine;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author czy
 */
public class ScriptContext {
    private Map<String, Object> variables = new LinkedHashMap<>();

    public void setVariable(String name, Object value) {
        if (name != null && value != null) {
            variables.put(name, value);
        }
    }

    public void setVariables(Map map) {
        if (map != null) {
            variables.putAll(map);
        }
    }

    public Object getVariable(String name) {
        if (name != null) {
            return variables.get(name);
        }

        return null;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }
}
