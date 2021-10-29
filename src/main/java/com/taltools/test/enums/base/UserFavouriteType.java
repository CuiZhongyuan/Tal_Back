package com.taltools.test.enums.base;

/**
 * @author Chris Yin
 */
public enum UserFavouriteType {
    RESERVED_DO_NOT_USE,     // 0
    API,                     // 1
    CASE,                    // 2
    PLAN,                    // 3
    RESERVED_DO_NOT_USE_1,   // 4
    ORG,                     // 5
    BU,                      // 6
    PROD,                    // 7
    COL,                     // 8
    ;

    public static UserFavouriteType valueOf(int value) {
        return UserFavouriteType.values()[value];
    }

    public int value() {
        return this.ordinal();
    }
}
