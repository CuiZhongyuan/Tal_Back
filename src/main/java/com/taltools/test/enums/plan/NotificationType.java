package com.taltools.test.enums.plan;

import lombok.Getter;

import java.util.Objects;

/**
 * @author Chris Yin
 */
@Getter
public enum NotificationType {
    YACH("yach"),
    ;

    private String type;

    NotificationType(String type) {
        this.type = type;
    }

    public static NotificationType valueOfType(String type) {
        for (NotificationType notificationType : NotificationType.values()) {
            if (Objects.equals(notificationType.getType(), type)) {
                return notificationType;
            }
        }
        throw new IllegalArgumentException(String.format("NotificationType type{%s}", type));
    }
}
