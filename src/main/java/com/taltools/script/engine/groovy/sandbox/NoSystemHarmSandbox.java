package com.taltools.script.engine.groovy.sandbox;

import org.kohsuke.groovy.sandbox.GroovyInterceptor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class NoSystemHarmSandbox extends GroovyInterceptor {
    private static final Set<String> ALLOWED_METHODS;
    static {
        ALLOWED_METHODS = new HashSet<>();
        ALLOWED_METHODS.addAll(Arrays.asList("currentTimeMillis", "nanoTime", "lineSeparator", "arraycopy"));
    }
    @Override
    public Object onStaticCall(Invoker invoker, Class receiver, String method, Object... args) throws Throwable {
        if (Objects.equals(receiver, System.class) && !ALLOWED_METHODS.contains(method))  {
            throw new SecurityException("不允许调用System." + method + "()");
        }
        return super.onStaticCall(invoker, receiver, method, args);
    }
}
