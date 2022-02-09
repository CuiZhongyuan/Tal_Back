import org.junit.Test;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class EnterStringPrintSplit {
    /**
     * 线程池-Runable并发方式
     */
    @Test
    public void poolRunableTest(){
        //1、获取线程池服务执行对象
       ExecutorService e =  Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            int x=i;
            //2、提交放入线程池的任务，继承Runable接口
           Runnable runnable = new Runnable(){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"执行了"+x+"多少号");
                }
            };
           e.execute(runnable);
        }
        //3、关闭线程池服务
        e.shutdown();
    }
    /**
     * 集合的常用api,集合工具类Collections
     */
    @Test
    public void listTest(){
        List<String> list = new ArrayList<>();
        list.add("f");
        list.add("e");
        list.add("d");
        String[] str = {"a","b","c"};
        //添加
        Collections.addAll(list,str);
        System.out.println(list);
        //升序
        Collections.sort(list);
        System.out.println(list);
        //二分查找必须是升序的集合
        System.out.println(Collections.binarySearch(list,"a"));
        //反转
        Collections.reverse(list);
        System.out.println(list);
        Map map = new HashMap();
        map.put("a","001");
        map.put("b","002");
        map.put("c","003");
    }
    /**
     * LinkedList：双向列表结构
     * 1、实现队列
     * 2、实现栈
     */
    //1、实现队列
    @Test
    public void queueTest(){
        Queue<String> q = new LinkedList<>();
        q.offer("001");
        q.offer("002");
        q.offer("003");
        System.out.println(q.poll());
        System.out.println(q.poll());
    }
    //2、实现栈
    @Test
    public void stackTest(){
        Deque<String> d = new LinkedList<>();
        d.push("001");
        d.push("002");
        d.push("003");
        System.out.println(d.poll());
        System.out.println(d.poll());
    }

    /**
     * 迭代器遍历ArrayList
     * 在使用迭代器遍历集合的过程中，不能使用元素集合对象删除元素
     * 只能使用迭代器对象去删除元素，否则会出现ConcurrentModificationException异常
     * 如果想删除元素，只能通过迭代器对象删除元素
     */
    @Test
    public void arrayList(){
        List<String> list = new ArrayList<>();
        list.add("tester");
        list.add("RD");
        list.add("PMO");
        Iterator<String> listIt = list.iterator();
        System.out.println(list.size());
        while (listIt.hasNext()){//如果有下一个元素，返回true
            System.out.println(listIt.next());
            //不可以用list.remove();
            //使用迭代器对象删除
            listIt.remove();
        }
        System.out.println(list.size());
    }
    /**
     * 递归:斐波那契数列或者阶乘
     */
    @Test
    public void testDg(){
        int num = 5;
        System.out.println(getDiGui(num));
    }

    private int getDiGui(int num) {
        if (num==0){
            return  0;
        }else if (num==1){
            return 1;
        }else if (num==2){
            return 2;
        }else {
            //规则调用自己，递归耗栈内存
           return getDiGui(num-1)+getDiGui(num-2);
        }

    }

    /**
     * 判断一个字符串是否是回文字符串
     */
    @Test
    public void testPalindrome(){
        String str = "abcba";
//        StringBuilder sb = new StringBuilder(str);
        //调用内部方法反转字符串
//        String str2 = sb.reverse().toString();
        //调用自写反转方法
        String str2 = returnReverse(str);
        System.out.println(str.equals(str2)?"是回文字符串":"不是回文字符串");
    }
    public String returnReverse(String str){
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = chars.length-1; i >=0 ; i--) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }
    /**
     * 输入字符串，请按长度为8拆分每个输入字符串并进行输出；
     * 长度不是8整数倍的字符串请在后面补数字0
     */
    @Test
    public void getResoult(){
        String str ="121312121212";
        //输出8位数字，不足8位补0
        outPrint(str);
    }
    private void outPrint(String line) {
        if(line.length()<=8){
            int num = 8 - line.length();
            StringBuffer stringBuffer = new StringBuffer(line);
            for (int i = 0; i < num; i++) {
                stringBuffer.append(0);
            }
            System.out.println(stringBuffer.toString());
        }else{
            //str＝str.substring(int beginIndex，int endIndex);
            // 截取str中从beginIndex开始至endIndex结束时的字符串，并将其赋值给str;
            String substring = line.substring(0, 8);
            System.out.println(substring);
            //str＝str.substring(int beginIndex);
            // 截取掉str从首字母起长度为beginIndex的字符串，将剩余字符串赋值给str；
            System.out.println(line.substring(8));
            outPrint(line.substring(8));
        }
    }

    /**
     * 十进制转换二进制方法,再有二进制转成十进制
     */

    @Test
    public void testTwo(){
        int num = 256;
        System.out.println(getBiary(num));
        System.out.println(getInterge(getBiary(num)));
    }

    /**
     * 将二进制转换成十进制
     * @param biary:二进制
     * @return
     */
    private int getInterge(String biary) {
        int sum =0;
        //10110
        //2*43210
        for (int i = 0; i <biary.length(); i++) {
            char c = biary.charAt(i);
            //parseInt用于将字符串参数作为有符号的十进制整数进行解析
            int j = Integer.parseInt(String.valueOf(c),10);
//            int j = Integer.valueOf(String.valueOf(c));
            //getTwoPower：次方函数实现
            sum+=j*getTwoPower(2,biary.length()-i-1);
        }
        return sum;
    }

    /**
     * 次方函数实现
     * @param j：底数
     * @param i：指数
     * @return
     */
    private int getTwoPower(int j, int i) {
        int a=1;
        for (int k = 0; k <i ; k++) {
           a*=j;
        }
        return a;
    }

    /**
     * 十进制转成二进制实现
     * @param num:十进制数
     * @return
     */
    private static  String getBiary(int num) {
        StringBuilder sbd = new StringBuilder();
        while (num>0){
            int x = num%2;
            sbd.append(x);
            num/=2;
        }
        sbd.reverse();
        return sbd.toString();
    }
}
