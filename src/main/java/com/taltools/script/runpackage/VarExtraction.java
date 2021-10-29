package com.taltools.script.runpackage;

import com.taltools.entity.Extraction;
import com.taltools.logger.SimpleLogger;

import java.util.Map;

/**
 * @author cyz
 */
public interface VarExtraction {
    Map<String, String> extract(StepResponseData data, Extraction extraction, SimpleLogger logger);
}
