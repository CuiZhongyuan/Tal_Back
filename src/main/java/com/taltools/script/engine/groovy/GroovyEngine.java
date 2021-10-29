package com.taltools.script.engine.groovy;

import com.taltools.script.engine.ScriptContext;
import com.taltools.script.engine.ScriptEngine;
import com.taltools.script.engine.groovy.sandbox.NoRuntimeSandbox;
import com.taltools.script.engine.groovy.sandbox.NoSystemHarmSandbox;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import groovy.transform.ThreadInterrupt;
import groovy.transform.TimedInterrupt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ASTTransformationCustomizer;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.kohsuke.groovy.sandbox.SandboxTransformer;


import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author czy
 */
@Slf4j
public class GroovyEngine implements ScriptEngine {
    private final Cache<String, Class<? extends Script>> lruCache;
    private static final Integer INTERRUPT_TIME_OUT = 15;
    private static final CompilerConfiguration config;

    private static final NoRuntimeSandbox NO_RUNTIME_SANDBOX;
    private static final NoSystemHarmSandbox NO_SYSTEM_HARM_SANDBOX;

    static {
        config = new CompilerConfiguration();
        // 添加默认import
        config.addCompilationCustomizers(new ImportCustomizer().addImports(
                "com.taltools.script.exception.StepFailureException",
                "com.jayway.jsonpath.JsonPath",
                "com.alibaba.fastjson.JSONObject",
                "com.alibaba.fastjson.JSONArray",
//                "groovy.json.JsonSlurper",
                "org.apache.commons.codec.digest.DigestUtils",
                "org.apache.commons.codec.digest.HmacUtils"));
        // 添加线程中断拦截器
        config.addCompilationCustomizers(new ASTTransformationCustomizer(ThreadInterrupt.class));
        // 添加线程中断拦截器，可中断超时线程，当前定义超时时间为30s
        config.addCompilationCustomizers(new ASTTransformationCustomizer(Collections.singletonMap("value", INTERRUPT_TIME_OUT), TimedInterrupt.class));
        // 沙盒环境
        config.addCompilationCustomizers(new SandboxTransformer());

        NO_RUNTIME_SANDBOX = new NoRuntimeSandbox();
        NO_SYSTEM_HARM_SANDBOX = new NoSystemHarmSandbox();
    }

    {
        lruCache = CacheBuilder.newBuilder()
                .maximumSize(1000) //最大容量
                .expireAfterAccess(12, TimeUnit.HOURS) //缓存过期时长
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())// 设置并发级别为cpu核心数
                .build();
    }

    @Override
    public ScriptContext runScript(String scriptContent) {
        return runScript(scriptContent, null);
    }

    @Override
    public ScriptContext runScript(String scriptContent, ScriptContext context) {
        if (scriptContent == null || scriptContent.isEmpty()) {
            throw new IllegalArgumentException("输入的script内容不能为空");
        }
        log.debug("执行Groovy脚本: {}", scriptContent);
        Binding binding = generateBinding(context);
        parseScript(scriptContent, binding).run();
        ScriptContext retContext = new ScriptContext();
        retContext.setVariables(binding.getVariables());
        return retContext;
    }

    public long getCacheCount() {
        return lruCache.size();
    }

    protected Script parseScript(String scriptContent, Binding binding) {
        String md5 = DigestUtils.md5Hex(scriptContent);
        String sha1 = DigestUtils.sha1Hex(scriptContent);
        String key = md5 + "-" + sha1;
        Class<? extends Script> scriptClass = lruCache.getIfPresent(key);
        if (scriptClass == null) {
            registerSandbox();
            GroovyShell shell = new GroovyShell(config);
            Script script = shell.parse(scriptContent);
            scriptClass = script.getClass();
            lruCache.put(key, scriptClass);
            log.debug("未命中脚本LRU缓存，创建脚步对象并存入缓存，当前缓存数量: {}", getCacheCount());
        } else {
            log.debug("命中脚本LRU缓存, key: {}, 当前缓存数量: {}", key, getCacheCount());
        }
        return InvokerHelper.createScript(scriptClass, binding);
    }

    protected void registerSandbox() {
        NO_RUNTIME_SANDBOX.register();
        NO_SYSTEM_HARM_SANDBOX.register();;
    }

    protected Binding generateBinding(ScriptContext context) {
        if (context != null) {
            return new Binding(new LinkedHashMap<>(context.getVariables()));
        }
        return new Binding();
    }
}
