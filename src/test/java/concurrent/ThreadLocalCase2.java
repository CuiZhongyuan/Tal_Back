package concurrent;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal 使用场景
 * */
public class ThreadLocalCase2 {
    /**
     * 场景1，ThreadLocal 用作保存每个线程独享的对象，为每个线程都创建一个副本，
     * 这样每个线程都可以修改自己所拥有的副本, 而不会影响其他线程的副本，确保了线程安全。
     * */
    //eg:用于保存线程不安全的工具类，典型的需要使用的类就是 SimpleDateFormat
    /**
     * Executors是一个线程池工厂类，里面有许多静态方法，供开发者调用。
     * 该方法返回一个固定线程数量的线程池，该线程池池中的线程数量始终不变。
     * 当有一个新的任务提交时，线程池中若有空闲线程，则立即执行。
     * 若没有，则新的任务会被暂存在一个任务队列中，待有线程空闲时，便处理在任务队列中的任务
     * 默认等待队列长度为Integer.MAX_VALUE
     * */
    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args){
        for (int i=0;i<11;i++){
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String getDate = new ThreadLocalCase2().date(finalI);
                    System.out.println("当前线程数"+Thread.currentThread().getName()+"当前时间"+getDate);
                }
            });
        }
        threadPool.shutdown();
    }
    public  String date(int i ){
        Date date = new Date(1000*i);
        SimpleDateFormat simpleDateFormat = ThreadLocalDemo.getDate.get();
        return simpleDateFormat.format(date);
    }
}

class  ThreadLocalDemo {
    public static ThreadLocal<SimpleDateFormat> getDate = new ThreadLocal<SimpleDateFormat>(){
        protected SimpleDateFormat initialValue(){
            return new SimpleDateFormat("mm:sss");
        }
    };
}
