package com.taltools.script.engine.groovy.sandbox;

import org.kohsuke.groovy.sandbox.GroovyInterceptor;

public class NoRuntimeSandbox extends GroovyInterceptor {
    @Override
    public Object onStaticCall(Invoker invoker, Class receiver, String method, Object... args) throws Throwable {
        if (receiver == Runtime.class) {
            throw new SecurityException("不允许调用RunTime");
       }
        return super.onStaticCall(invoker, receiver, method, args);
    }
}
