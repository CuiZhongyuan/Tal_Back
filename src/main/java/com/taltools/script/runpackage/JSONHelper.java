package com.taltools.script.runpackage;

import com.taltools.test.enums.base.CommonKeyValuePair;
import com.taltools.utils.CommonUtils;
import com.alibaba.fastjson.JSONObject;

import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author czy
 */
public class JSONHelper {
    public static String toJSONString(Object obj) {
        if (obj instanceof String) return CommonUtils.checkAndAs(obj, String.class);
        if (obj instanceof List) {
            List srcList = CommonUtils.checkAndAs(obj, List.class);
            if (!srcList.isEmpty() && srcList.get(0) instanceof CommonKeyValuePair) {
                return JSONObject.toJSONString(
                        srcList.stream().map(item -> {
                            CommonKeyValuePair pair = CommonUtils.checkAndAs(item, CommonKeyValuePair.class);
                            return new AbstractMap.SimpleEntry(pair.getKey(), pair.getValue());
                        }).collect(Collectors.toList()));
            }
        }
        return JSONObject.toJSONString(obj);
    }
}
