package com.taltools.test.enums.plan;

import lombok.Getter;


/**
 * @author Chris Yin
 */
@Getter
public enum TestPlanTriggerType {
    MANUAL_TRIGGER("手工触发", 1),
    PLAN_TRIGGER("计划触发", 2),
    TIMER_TRIGGER("定时器触发", 3),
    THIRD_PARTY_TRIGGER("第三方调用触发", 4),
    ;

    private String name;
    private int typeVal;

    TestPlanTriggerType(String name, int typeVal) {
        this.name = name;
        this.typeVal = typeVal;
    }

    public static TestPlanTriggerType valueOfTypeVal(int typeVal) {
        for (TestPlanTriggerType type : TestPlanTriggerType.values()) {
            if (type.typeVal == typeVal) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("无效的TestPlanTriggerType typeVal{%s}", typeVal));
    }
}
