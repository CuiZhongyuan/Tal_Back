package com.taltools.test.enums.exec;

/**
 * @author Chris Yin
 */
public enum TestCaseFlag {
    RESERVED,     // 0
    NORMAL,       // 1
    SKIP,         // 2
    ;
    public static TestCaseFlag valueOf(int value) {
        return TestCaseFlag.values()[value];
    }

    public int value() {
        return this.ordinal();
    }
}
