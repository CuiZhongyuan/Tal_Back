import com.taltools.utils.DateUtils;
import com.taltools.utils.RestTemplateUtils;

public class ThreadLocalCase {
    private static  String URL="http://127.0.0.1:8080/api/getPage?pageNum=1&pageSize=10";
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();
    /**
     * 多线程学习
     */
    public static void main(String[] args){
        //i:创建线程数，两个线程并发，且每个线程内并发两个请求
        for (int i=0;i<2;i++){
            new Thread(new runThreadLocal(URL)).start();
        }
    }
    static class runThreadLocal implements Runnable{
        private String url;
        //有参构造方法
        public runThreadLocal(String url){
            this.url=url;
        }
        @Override
        public void run() {
            try{
                //线程内并发数，当前线程内并发俩个
                for (int i=0;i<1;i++){
                    //发送http请求获取响应结果
                    String getBody = RestTemplateUtils.get(url,String.class).getBody();
                    threadLocal.set(getBody);
                    System.out.println("当前线程名："+Thread.currentThread().getName()+"当前线程第几次并发数："+i+"---:"+DateUtils.getSystime()+": "+threadLocal.get());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
