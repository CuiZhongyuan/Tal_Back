import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Asynchronous {
    /**
     * 异步操作
     * 打印1-n，如果数字是n/2,那么久插入一个为期2s的任务
     */
    public static void main(String[] args){

        Scanner scanner= new Scanner(System.in);
        int num = scanner.nextInt();
        sendSingle(num);
    }

    private static void sendSingle(int num) {
        for (int i=0;i<num;i++){
            if (i==num/2){
                sendMeeage();
            }
            System.out.println(i);
        }
    }

    private static void sendMeeage() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消息执行完毕");
        });
    }
}
