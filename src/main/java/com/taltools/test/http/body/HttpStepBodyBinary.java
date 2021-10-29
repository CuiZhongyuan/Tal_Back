package com.taltools.test.http.body;

import lombok.Data;

/**
 * @author Chris Yin
 */
@Data
public class HttpStepBodyBinary {
    private String value;    // value存binary对应的base64
    private String fileName; // 原始文件名
}
