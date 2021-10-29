package com.taltools.test.enums.exec;

import lombok.Getter;

import java.util.Objects;

/**
 * @author Chris Yin
 */
@Getter
public enum DatabaseType {
    MYSQL("MySQL", "mysql", "mysql", "com.mysql.cj.jdbc.Driver"),
    ;

    private String name;
    private String code;
    private String schema;
    private String defaultDriver;

    DatabaseType(String name, String code, String schema, String defaultDriver) {
        this.name = name;
        this.code = code;
        this.schema = schema;
        this.defaultDriver = defaultDriver;
    }

    public static DatabaseType valueOfCodeString(String code) {
        for (DatabaseType type : DatabaseType.values()) {
            if (Objects.equals(type.code, code)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("无效的DatabaseType code{%s}", code));
    }
}
