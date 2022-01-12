package com.taltools.service.impl;


import com.taltools.entity.AnalysisDiffCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 解析git diff结果算法
 */
@Service
@Slf4j
public class AnalysisCodeServiceImpl {


    /**
     * 根据diff结果取出新代码
     * @param gitDiffList by Shell Git Diff Command Console Info
     * @return
     */
    public List<AnalysisDiffCode> analysisDiffCode(List<String> gitDiffList) {
        if(CollectionUtils.isEmpty(gitDiffList)){
            return Collections.emptyList();
        }
        String line = "";
        List<AnalysisDiffCode> resultList = new ArrayList<>();
        AnalysisDiffCode analysisDIffCode = new AnalysisDiffCode();
        List<AnalysisDiffCode.LineWithCodeList> lineCodeList = new ArrayList<AnalysisDiffCode.LineWithCodeList>();
        AnalysisDiffCode.LineWithCodeList lineWithCodeList = new AnalysisDiffCode.LineWithCodeList();
        List<String> diffCode = new ArrayList<String>();
        int i = 0;  //定义是否第一次进入+++
        int j = 0;  //定义是否第一次进入@@
        int lineIndex = 0; //计数 - 代码所在行数
        int lineNum = 0; // 计数 - 代码初始行数
        for (int index = 0; index < gitDiffList.size(); index++) {
            if (gitDiffList.get(index).startsWith("+++") && !gitDiffList.get(index).contains("/dev/null")) {
                line = gitDiffList.get(index).substring(5);
                List<String> breakUpPath = java.util.Arrays.asList(line.split("/"));

                if (j != 0) { //当j不等于0时，说明已经进入一次@@，然后将line num写入实体
                    lineWithCodeList.setDiffCodeList(diffCode);
                    lineCodeList.add(lineWithCodeList);
                }

                j = 0;
                if (i != 0) {
                    analysisDIffCode.setLineCodeList(lineCodeList);
                    resultList.add(analysisDIffCode);
                    diffCode = new ArrayList<>();
                    lineWithCodeList = new AnalysisDiffCode.LineWithCodeList();
                    analysisDIffCode = new AnalysisDiffCode();
                    lineCodeList = new ArrayList<>();
                }
                analysisDIffCode.setFileName(breakUpPath.get(breakUpPath.size() - 1));
                analysisDIffCode.setPath(line);
                i += 1;
            }

            if (gitDiffList.get(index).startsWith("@@")) {
                int begin = gitDiffList.get(index).indexOf("+");
                int end = gitDiffList.get(index).indexOf(",", begin + 1);
                if(end < 0 ){
                    end = gitDiffList.get(index).indexOf(" ", begin + 1);
                }
                try {
                    if(gitDiffList.get(index).length() > 0){
                        line = gitDiffList.get(index).substring(begin + 1, end);
                    }else {
                        continue;
                    }
                }
                catch (Exception e) {
                    //line = gitDiffList.get(index).substring(begin + 1, begin + 2);
                    log.error("Git Diff解析改动行出现解析异常！行是：{}，起始位置：{}，结束位置：{}，内容是：{}，异常是：{}", gitDiffList.get(index), begin, end, gitDiffList.get(index) , e.getMessage(), e);
                }
                try {
                    lineNum = Integer.parseInt(line);
                } catch (Exception e) {
                    log.error("Git Diff解析改动行转成int出现异常！行号是{}，异常是：{}", index, e.getMessage(), e);
                    continue;
                }

                if (j != 0) {
                    lineWithCodeList.setDiffCodeList(diffCode);
                    lineCodeList.add(lineWithCodeList);
                    diffCode = new ArrayList<>();
                    lineWithCodeList = new AnalysisDiffCode.LineWithCodeList();
                }
                lineWithCodeList.setLineNum(line);
                lineIndex = 0;
                j += 1;
            }

            if (gitDiffList.get(index).indexOf(" ") == 0) {
                lineIndex += 1;
            }

            if (!gitDiffList.get(index).equals("") && gitDiffList.get(index).substring(0, 1).equals("+") && !gitDiffList.get(index).contains("+++")) {
                lineIndex += 1;
                int lineNums = lineNum + lineIndex - 1;
                line = gitDiffList.get(index).substring(1);
                diffCode.add(line + " ::: " + lineNums);
            }
        }
        return resultList;
    }


}
