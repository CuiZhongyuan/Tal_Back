package com.taltools.test.enums.exec;

import lombok.Getter;

import java.util.Objects;

/**
 * @author Chris Yin
 */
@Getter
public enum ExtractionType {
    JSON_PATH("JSON Path", "jsonpath"),
    REGEXP("正则表达式", "regexp")
    ;

    private String name;
    private String code;

    ExtractionType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static ExtractionType valueOfCodeString(String code) {
        for (ExtractionType type : ExtractionType.values()) {
            if (Objects.equals(type.code, code)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("无效的ExtractionType code{%s}", code));
    }
}
