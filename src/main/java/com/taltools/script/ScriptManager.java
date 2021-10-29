package com.taltools.script;

import com.taltools.script.engine.ScriptEngine;
import com.taltools.script.engine.groovy.GroovyEngine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author czy
 */
public class ScriptManager {
    private static volatile ScriptManager instance;
    private Map<String, ScriptEngine> engines = new ConcurrentHashMap<>();

    static {
        ScriptManager.getInstance().registerEngine("Groovy", new GroovyEngine());
    }

    public static ScriptManager getInstance() {
        if (instance == null) {
            synchronized (ScriptManager.class) {
                if (instance == null) {
                    instance = new ScriptManager();
                }
            }
        }

        return instance;
    }

    public void registerEngine(String name, ScriptEngine engine) {
        if (name != null && engine != null) {
            engines.put(name.toLowerCase(), engine);
        }
    }

    public ScriptEngine getEngine(String name) {
        if (name != null) {
            return engines.get(name.toLowerCase());
        }

        return null;
    }
}
