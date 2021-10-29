package com.taltools.test.http.body;

import lombok.Data;

import java.util.List;

/**
 * @author Chris Yin
 */
@Data
public class HttpStepBody {
    private HttpStepBodyRaw raw;                          // 如果为raw类型
    private List<HttpStepBodyFormData> formData;          // 如果为form-data类型
    private List<HttpStepBodyUrlEncoded> formUrlEncoded;  // 如果为x-www-form-urlencoded类型
    private HttpStepBodyBinary binary;                    // 如果为binary类型
}
