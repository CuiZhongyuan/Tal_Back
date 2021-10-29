package com.taltools.test.enums.base;

import lombok.Getter;

/**
 * Description:
 * Author: Allen.Yang
 * Date: 2020/5/22 14:11
 */
public enum ResponseCode {
    SUCCESS(200, "成功"),
    ERROR(500, "服务内部异常"),
    DEPLOY_DOING_NOW(600, "部署进行中，待稍后完成可再进行部署"),

    // 7** 知音楼异常
    YACH_ERROR(700, "知音楼发送消息异常"),

    TOO_MANY_TEST_PLAN(900, "单次测试计划执行不能超过5个"),

    LOGIN_ERROR(1000, "用户登录异常"),

    // 5*** 测试报告异常相关
    REPORT_SEND_ERROR(5000, "测试报告发送异常"),

    // 3*** 提测异常相关
    SEND_TC_ERROR(3000, "发送提测知音楼消息异常"),

    // 4*** 提测异常相关
    OUT_SERVICE_ERROR(4000, "下游接口处理异常"),

    // 1*** 参数异常
    ARGUMENTS_ERROR(1100, "参数校验失败不合法"),

    // 磐石自定义错误码
    DATA_NOT_AVAILABLE(6000, "数据不可用"),
    DATA_CANNOT_DELETE(6100, "数据不能删除");

    @Getter
    private int code;

    @Getter
    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
