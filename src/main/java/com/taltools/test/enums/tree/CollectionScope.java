package com.taltools.test.enums.tree;

/**
 * Collection 作用域enum，请不要随意修改对应顺序
 */
public enum CollectionScope {
    RESERVED_DO_NOT_USE,     // 0
    API_AND_CASE,            // 1
    SCENE,                   // 2
    PLAN,                    // 3
    ;

    public static CollectionScope valueOf(int value) {
        return CollectionScope.values()[value];
    }

    public int value() {
        return this.ordinal();
    }
}
