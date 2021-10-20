import java.util.Scanner;

public class Synchronous {
    /**
     * 同步执行操作
     * 编写程序交替打印：奇偶数
     */
    public static int count;
    public static int limit;
    public static Object moniter = new Object();
    public static void main(String[] args){
        Scanner scanner= new Scanner(System.in);
        int num = scanner.nextInt();
        limit = num;
        alterPrint(num);
    }

    private static void alterPrint(int num) {
        //交替打印奇偶数
        for (int i =0 ;i<num;i++) {
            System.out.println(i);
            count = 0;
            //交替打印实现
            Thread threadOne = new Thread(() -> {
                synchronized (moniter) {
                    while (count++ < limit) {
                        System.out.println(count + Thread.currentThread().getName());
                        moniter.notify();
                    }
                    try {
                        moniter.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "线程1");
            //交替打印实现
            Thread threadTwo = new Thread(() -> {
                synchronized (moniter) {
                    while (count++ < limit) {
                        System.out.println(count + Thread.currentThread().getName());
                        moniter.notify();
                    }
                    try {
                        moniter.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "线程2");
            threadOne.start();
            threadTwo.start();
        }
    }
}
