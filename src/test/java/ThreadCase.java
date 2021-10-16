public class ThreadCase {
    private static final String TEXT="测试多线程，学习多线程"+"。。。。。。";
    /**
     * 多线程学习
     */
    public static void main(String[] args){
        System.out.println("程序开始，执行的线程名叫："+Thread.currentThread().getName());
        //需要启动几个线程执行程序
        for (int i=0;i<=3;i++){
            Thread thread = new Thread(new PrintText(TEXT,200),"我的线程--"+i);
            thread.start();
        }
        System.out.println("启动的线程结束，名字叫做"+Thread.currentThread().getName());

    }
    static class PrintText implements Runnable{
        private String text;
        private long time;
        public PrintText(String text,long time){
            this.text=text;
            this.time=time;
        }

        @Override
        public void run() {
            try {
                System.out.println("执行这段代码的名字叫做"+Thread.currentThread().getName());
                printRseult(text,time);
                System.out.println(Thread.currentThread().getName()+"执行结果");
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        private void printRseult(String text, long time) throws InterruptedException {
            for (char c:text.toCharArray()){
                Thread.sleep(time);
                System.out.print(c);
            }
        }


    }

}
