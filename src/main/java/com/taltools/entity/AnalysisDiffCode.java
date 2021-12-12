package com.taltools.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class AnalysisDiffCode {
    String fileName;
    String path;
    List<LineWithCodeList> lineCodeList;

    @Setter
    @Getter
    public static class LineWithCodeList{
        String lineNum;
        List<String> diffCodeList;
    }
}
