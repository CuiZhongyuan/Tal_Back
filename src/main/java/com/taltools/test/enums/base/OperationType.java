package com.taltools.test.enums.base;

/**
 * @author Chris Yin
 */
public enum OperationType {
    ARCHIVE,                   // 0
    NEW,                       // 1
    UPDATE,                    // 2
    DELETE,                    // 3
    ;

    public static OperationType valueOf(int value) {
        return OperationType.values()[value];
    }

    public int value() {
        return this.ordinal();
    }
}
