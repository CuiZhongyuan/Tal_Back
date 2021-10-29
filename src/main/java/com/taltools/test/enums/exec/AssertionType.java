package com.taltools.test.enums.exec;

import lombok.Getter;

import java.util.Objects;

/**
 * @author Chris Yin
 */
@Getter
public enum AssertionType {
    RESPONSE_CODE("状态码", "rescode"),
    JSONP("JSONP", "jsonp"),
    JSON_PATH("JSON Path", "jsonpath"),
    REGEXP("正则表达式", "regexp"),
    RESPONSE_TIME("响应时间", "restime")
    ;

    private String name;
    private String code;

    AssertionType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static AssertionType valueOfCodeString(String code) {
        for (AssertionType type : AssertionType.values()) {
            if (Objects.equals(type.code, code)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("无效的AssertionType code{%s}", code));
    }
}
