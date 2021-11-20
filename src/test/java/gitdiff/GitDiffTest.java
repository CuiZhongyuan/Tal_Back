package gitdiff;

import com.taltools.utils.ShellUtils;
import org.junit.Test;

import java.util.List;

public class GitDiffTest {
    @Test
    public void gitDiff(){
        String shellCommand = "git diff /Users/cuizhongyuan/Desktop/work/talcoco/source_code//full /Users/cuizhongyuan/Desktop/work/talcoco/classes//data/push_data//workspace/";
       List<String> list =  ShellUtils.execShellByThread(shellCommand);
       for (String str : list){
           System.out.println(str);
       }
    }
}
