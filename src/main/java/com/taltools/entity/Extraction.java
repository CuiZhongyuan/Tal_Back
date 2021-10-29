package com.taltools.entity;

import lombok.Data;

/**
 * @author czy
 */
@Data
public class Extraction {
    private String type;        // 类型
    private String target;      // 目标，例如body，headers
    private String variable;    // 变量名
    private String expression;  // 表达式
    private String value;       // 值，待定，记录上次调试数据值？或者默认值？
    private String description; // 描述
}
