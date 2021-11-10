import com.taltools.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ReteyDemo {
    @Test
    public void runDemo(){
        String url = "https://www.baidu.c1om/";
        for (int i=0;i<3;i++){
            try {
                 int  num = result(url);
                 if (num ==200){
                     System.out.println("请求成功");
                     return;
                 }
            }catch (Exception e){
                log.error("已超过重试次数",e);
            }
        }

    }
    public Integer result(String url){
        return  RestTemplateUtils.get(url,String.class).getStatusCodeValue();
    }
}
