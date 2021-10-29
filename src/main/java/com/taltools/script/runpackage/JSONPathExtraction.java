package com.taltools.script.runpackage;

import com.taltools.entity.Extraction;
import com.taltools.logger.SimpleLogger;
import com.jayway.jsonpath.JsonPath;

import java.util.Collections;
import java.util.Map;

/**
 * @author czy
 */
public class JSONPathExtraction implements VarExtraction {
    @Override
    public Map<String, String> extract(StepResponseData data, Extraction extraction, SimpleLogger logger) {
        Object targetData = data.getResponseData(extraction.getTarget());
        logger.log("通过JSONPath提取变量, 变量名: %s, 表达式: %s", extraction.getVariable(), extraction.getExpression());
        try {
            String json = JSONHelper.toJSONString(targetData);
            String extractValue = JsonPath.read(json, extraction.getExpression()).toString();
            logger.log("通过JSONPath提取的变量值: " + extractValue);
            return Collections.singletonMap(extraction.getVariable(), extractValue);
        } catch (Exception ex) {
            logger.log("通过JSONPath提取变量遇到错误: " + ex.getMessage());
        }
        return null;
    }
}
