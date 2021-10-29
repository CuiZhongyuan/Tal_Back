package com.taltools.entity;


import lombok.Data;

import java.util.List;

/**
 * @author czy
 */
@Data
public abstract class CommonStepData {
    private Integer id;                    // 步骤id
    private Integer typeId;                // 步骤类型id
    private String name;                   // 步骤名称
    private Boolean referEnv;              // 是否引用环境
    private Boolean shareCookie;           // 是否使用共享cookie
    private Integer sleep;                 // 等待时间，延迟
    private Integer timeout;               // 超时时间
    private Integer failureRerunMax;       // 失败重试次数
}
