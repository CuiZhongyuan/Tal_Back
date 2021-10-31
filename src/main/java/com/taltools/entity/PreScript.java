package com.taltools.entity;

import lombok.Data;

/**
 * @author Chris czy
 */
@Data
public class PreScript {
    private Integer scriptId;  // 如果引用了公共script，此为script_config表id
    private Script script;     // 如果是自定义脚本
    private boolean ignoreException; // 是否忽略脚步异常
}
