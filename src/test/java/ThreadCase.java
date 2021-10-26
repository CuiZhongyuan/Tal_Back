import com.taltools.utils.RestTemplateUtils;

public class ThreadCase {
    private static  String URL="http://127.0.0.1:8080/api/getPage?pageNum=1&pageSize=10";
    /**
     * 多线程学习示例
     */
    public static void main(String[] args){
        System.out.println("程序开始，执行的线程名叫："+Thread.currentThread().getName());
        //需要启动几个线程执行程序
        for (int i=0;i<3;i++){
            Thread thread = new Thread(new PrintText(URL,200),"当前的线程名称--"+i);
            thread.start();
        }
        System.out.println("启动的线程结束，名字叫做"+Thread.currentThread().getName());

    }
    static class PrintText implements Runnable{
        private String url;
        private long time;
        public PrintText(String url,long time){
            this.url=url;
            this.time=time;
        }

        @Override
        public void run() {
            try {
                System.out.println("执行这段代码的名字叫做"+Thread.currentThread().getName());
                printRseult(url,time);
                System.out.println(Thread.currentThread().getName()+"执行结果");
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        private void printRseult(String url, long time) throws InterruptedException {
            Thread.sleep(time);
            System.out.println("---------http并发请求--------------");
            System.out.println(RestTemplateUtils.get(url,String.class).getBody());
        }
    }

}
