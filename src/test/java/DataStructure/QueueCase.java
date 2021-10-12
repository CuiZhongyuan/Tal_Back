package DataStructure;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 队列解决瑟夫环问题
 */
public class QueueCase {
    @Test
    public  void queueTest() {
        ring(10, 5);
    }
    public static void ring(int n, int m) {
        LinkedList<Integer> q = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            q.add(i);
        }
        int k = 2;
        int element = 0;
        int i = 1;
        for (; i<k; i++) {
            element = q.poll();
            q.add(element);
        }
        i = 1;
        while (q.size() > 0) {
            element = q.poll();
            if (i < m) {
                q.add(element);
                i++;
            } else {
                i = 1;
                System.out.println(element);
            }
        }

    }

    @Test
    public void ttt(){
        String str = "123";
        String[] num = str.split("\"");
        for (int i=0;i<num.length;i++){
            int integer = Integer.valueOf(num[i]);
            System.out.println(integer);
        }

    }


}
