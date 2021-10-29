package script;

import com.taltools.logger.SimpleLogger;
import com.taltools.script.ScriptManager;
import com.taltools.script.engine.ScriptContext;
import com.taltools.script.engine.ScriptEngine;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author czy
 */
public class JavaScriptEngineTest {
    private static Path MOCKJS_PATH = Paths.get(".", "src", "test", "java", "cn", "speiyou", "qa", "auto", "exec", "script").resolve("mock.js");

    @Test
    public void testJavaScriptRun() throws IOException {
        ScriptEngine scriptEngine = ScriptManager.getInstance().getEngine("javascript");
        String mockjs = new String(Files.readAllBytes(MOCKJS_PATH));
        ScriptContext context = new ScriptContext();
        context.setVariable("_req", createReq());
        Map<String, String> outParams = new LinkedHashMap<>();
        context.setVariable("_res", outParams);
        ScriptContext ret = scriptEngine.runScript(mockjs + "\n" + createTestScript1(), context);
        System.out.println(ret.getVariables());

        ret = scriptEngine.runScript(mockjs + "\n" + createTestScript2(), context);
        System.out.println(ret.getVariables());

        ret = scriptEngine.runScript(mockjs + "\n" + createTestScript3(), context);
        System.out.println(ret.getVariables());
        System.out.println(outParams.get("body"));

        try {
            ret = scriptEngine.runScript(mockjs + "\n" + createTestScript2());
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void testBasicRun() {
        ScriptEngine scriptEngine = ScriptManager.getInstance().getEngine("javascript");
        ScriptContext context = new ScriptContext();
        context.setVariable("_req", createReq());
        Map<String, String> outParams = new LinkedHashMap<>();
        //String script = "function test() { if (_req.method === 'POST') {return true} else {return false} }; test()";
        String script = "if (_req.method === 'GET') { true} else { false}";
        ScriptContext ret = scriptEngine.runScript(script, context);
        System.out.println(ret.getVariables());
    }

    @Test
    public void testBasicLoggerRun() {
        ScriptEngine scriptEngine = ScriptManager.getInstance().getEngine("javascript");
        SimpleLogger logger = new SimpleLogger();
        ScriptContext context = new ScriptContext();
        context.setVariable("_req", createReq());
        context.setVariable("logger", logger);
        Map<String, String> outParams = new LinkedHashMap<>();
        //String script = "function test() { if (_req.method === 'POST') {return true} else {return false} }; test()";
        String script = "if (_req.method === 'GET') { logger.log(_req.url) } else { logger.log(_req.method) }";
        ScriptContext ret = scriptEngine.runScript(script, context);
        System.out.println(ret.getVariables());
        System.out.println(logger.getLogs());
    }

    @Test(expected = RuntimeException.class)
    public void testSpecialCodeRun() {
        ScriptEngine scriptEngine = ScriptManager.getInstance().getEngine("javascript");
        ScriptContext context = new ScriptContext();
        context.setVariable("_req", createReq());
        Map<String, String> outParams = new LinkedHashMap<>();
        String script = "if (_req.method === 'GET') { var sys = Java.type('java.lang.System'); sys.exit(1) } else { false}";
        ScriptContext ret = scriptEngine.runScript(script, context);
        System.out.println(ret.getVariables());
    }

    public String createTestScript1() {
        return "Mock.Random.id()";
    }

    public String createTestScript2() {
        return "JSON.stringify(Mock.mock(" +
                "{\n" +
                "    \"ret\": function() {return _req.url},\n" +
                "    \"arr\":[0,1,24,5,6],\n" +
                "    \"data\":\n" +
                "      {\n" +
                "        \"mtime\": \"@datetime\",//随机生成日期时间\n" +
                "        \"score|1-800\": 800,//随机生成1-800的数字\n" +
                "        \"rank|1-100\":  100,//随机生成1-100的数字\n" +
                "        \"stars|1-5\": 5,//随机生成1-5的数字\n" +
                "        \"nickname\": \"@cname\",//随机生成中文名字\n" +
                "      }}" +
                "))";
    }

    public String createTestScript3() {
        return "_res.body = JSON.stringify(Mock.mock(" +
                "{\n" +
                "    \"ret\": function() {return _req.url},\n" +
                "    \"arr\":[0,1,24,5,6],\n" +
                "    \"data\":\n" +
                "      {\n" +
                "        \"mtime\": \"@datetime\",//随机生成日期时间\n" +
                "        \"score|1-800\": 800,//随机生成1-800的数字\n" +
                "        \"rank|1-100\":  100,//随机生成1-100的数字\n" +
                "        \"stars|1-5\": 5,//随机生成1-5的数字\n" +
                "        \"nickname\": \"@cname\",//随机生成中文名字\n" +
                "      }}" +
                "))";
    }

    public Map<String, Object> createReq() {
        Map<String, Object> ret = new LinkedHashMap<>();
        ret.put("url", "https://www.baidu.com");
        ret.put("method", "GET");
        return ret;
    }
}
