package gitdiff;

import com.taltools.entity.AnalysisDiffCode;
import com.taltools.service.impl.AnalysisCodeService;
import com.taltools.utils.ShellUtils;
import org.junit.Test;


import java.util.List;

public class GitDiffTest {
    @Test
    public void gitDiff(){
        String shellCommand = "git diff /Users/cuizhongyuan/Desktop/work/010_project/vueSpringboot/Tal_Back/src/test/java/gitdiff/diff1 /Users/cuizhongyuan/Desktop/work/010_project/vueSpringboot/Tal_Back/src/test/java/gitdiff/diff2";
       List<String> list =  ShellUtils.execShellByThread(shellCommand);
       for (String str : list){
           System.out.println(str);
       }
        AnalysisCodeService analysisCodeService = new AnalysisCodeService();
       List<AnalysisDiffCode> listCode = analysisCodeService.analysisDiffCode(list);
       for (AnalysisDiffCode a : listCode){
           for (AnalysisDiffCode.LineWithCodeList l : a.getLineCodeList()){
               for (String s: l.getDiffCodeList()){
                   System.out.println(">>>>>");
                   System.out.println("变动代码详情===>"+s);
                   System.out.println(">>>>>");
               }
           }
       }

    }
}
