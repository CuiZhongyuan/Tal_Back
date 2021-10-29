package com.taltools.test.enums.plan;

/**
 * @author Chris Yin
 */
public enum TestPlanExecutionStatus {
    NOT_START,          // 0
    IN_PROGRESS,        // 1
    COMPLETED,          // 2
    TIMEOUT,            // 3
    ;

    public static TestPlanExecutionStatus valueOf(int value) {
        return TestPlanExecutionStatus.values()[value];
    }

    public int value() {
        return this.ordinal();
    }
}
