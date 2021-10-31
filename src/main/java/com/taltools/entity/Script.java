package com.taltools.entity;

import lombok.Data;

/**
 * @author Chris czy
 */
@Data
public class Script {
    private String type;             // 脚本类型
    private String script;           // 脚本内容
    private boolean ignoreException; // 是否忽略脚步异常
}
