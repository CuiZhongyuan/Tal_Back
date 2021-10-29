package com.taltools.test.http.body;

import com.taltools.test.CommonKeyValuePair;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Chris Yin
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class HttpStepBodyFormData extends CommonKeyValuePair<String, HttpStepBodyFormDataValue> {
}
