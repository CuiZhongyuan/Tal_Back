package com.taltools.test.enums.exec;

/**
 * @author Chris Yin
 */
public enum TestStepType {
    RESERVED,     // 0
    HTTP,         // 1
    SQL,          // 2
    WEB_SOCKET,   // 3
    ;

    public static TestStepType valueOf(int value) {
        return TestStepType.values()[value];
    }

    public int value() {
        return this.ordinal();
    }
}
