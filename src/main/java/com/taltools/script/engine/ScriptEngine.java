package com.taltools.script.engine;

import org.springframework.stereotype.Service;

/**
 * @author czy
 */
public interface ScriptEngine {
    ScriptContext runScript(String scriptContent, ScriptContext context);
    ScriptContext runScript(String scriptContent);
}
