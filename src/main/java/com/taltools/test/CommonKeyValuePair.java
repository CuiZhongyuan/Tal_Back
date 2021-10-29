package com.taltools.test;

import lombok.Data;

/**
 * @author Chris Yin
 */
@Data
public class CommonKeyValuePair<K, V> {
    private Integer id;          // id 前端使用
    private K key;               // key
    private V value;             // value
    private String description;  // 描述
}
