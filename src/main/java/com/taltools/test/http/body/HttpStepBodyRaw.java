package com.taltools.test.http.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chris Yin
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HttpStepBodyRaw {
    private String raw;      // body内容
    private String mimeType; // 内容类型，例如 application/json，可调用 ContentType.getByMimeType("application/json") 转化
}
