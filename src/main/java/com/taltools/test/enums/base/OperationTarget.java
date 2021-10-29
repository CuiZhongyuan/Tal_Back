package com.taltools.test.enums.base;

/**
 * @author Chris Yin
 */
public enum OperationTarget {
    RESERVED_DO_NOT_USE,     // 0
    API,                     // 1
    CASE,                    // 2
    PLAN,                    // 3
    ;

    public static OperationTarget valueOf(int value) {
        return OperationTarget.values()[value];
    }

    public int value() {
        return this.ordinal();
    }
}
