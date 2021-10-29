package com.taltools.test.http.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chris Yin
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HttpStepBodyFormDataValue {
    private String type;     // 参见 HttpStepBodyFormDataValueType
    private String value;    // 如果是Binary或File，value存对应的base64
    private String fileName; // 如果是File类型，原始文件名
}
