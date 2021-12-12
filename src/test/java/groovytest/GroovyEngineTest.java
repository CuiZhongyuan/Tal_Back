package groovytest;

import com.taltools.test.http.HttpStepHeader;
import com.taltools.script.ScriptManager;
import com.taltools.script.engine.ScriptContext;
import com.taltools.script.engine.ScriptEngine;
import com.taltools.script.engine.groovy.GroovyEngine;
import com.taltools.utils.ExecUtil;
import com.taltools.utils.JsonUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author czy
 */
public class GroovyEngineTest {

    @Test
    public void testGroovy(){
        String req = "    outParams['name'] = testGroovy();\n" +
                "    static String testGroovy(){\n" +
                "        def name = 'java'\n" +
                "        def greeting = \"Hello ${name}\"\n" +
                "        return greeting\n" +
                "    }";
        GroovyEngine engine = new GroovyEngine();
        ScriptContext context = new ScriptContext();
        LinkedHashMap<String, Object> outParams = new LinkedHashMap<>();
        context.setVariable("outParams",outParams);
        ScriptContext ret = engine.runScript(req,context);
        System.out.println(JsonUtils.obj2json(ret));
        System.out.println(outParams);
    }

    @Test
    public void testGroovyRun() {
        GroovyEngine engine = new GroovyEngine();
        ScriptContext context = new ScriptContext();
        context.setVariable("inParams", createInParams());
        Map<String, String> outParams = new LinkedHashMap<>();
        outParams.put("key1","resp");
        context.setVariable("outParams", outParams);
        ScriptContext ret = engine.runScript("println(inParams['headers'][0].getKey()); outParams['myvar'] = 'yes'", context);
        System.out.println(JsonUtils.jsonFormatter(JsonUtils.mapToJson(ret.getVariables())));
    }

    @Test
    public void testGroovyRule() throws InterruptedException {
        ScriptEngine engine = ScriptManager.getInstance().getEngine("Groovy");
        for (int i = 0; i < 10; i++) {
            final int n = i;
            new Thread(() -> {
                if (n == 1)
                    engine.runScript("println(Thread.currentThread()); System.exit(1)");
                else if (n == 5)
                    engine.runScript("println(Thread.currentThread()); System.exit(1)");
                else if (n == 8)
                    engine.runScript("while(true){}");
                else
                    engine.runScript("println('normal'); k2 = 10");
            }).start();
        }

        Thread.sleep(20000);

        //System.out.println(engine.getCacheCount());
    }

    protected Map<String, Object> createInParams() {
        Map<String, Object> ret = new LinkedHashMap<>();
        List<HttpStepHeader> headers = new ArrayList<>();
        headers.add(ExecUtil.createHttpStepHeader("Content-Type", "application/json"));
        headers.add(ExecUtil.createHttpStepHeader("Cookie", "123"));
        ret.put("headers", headers);
        ret.put("body","200");
        return ret;
    }
}
