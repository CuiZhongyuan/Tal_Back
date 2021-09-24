package JavaBestToBadPractices;

import org.junit.Test;

/***
 * 循环拼接中拼接耗时对比,从结果来看：
 * 当有大量字符串拼接需要时，尤其是在循环中，考虑使用StringBuilder或StringBuffer
 */
public class StringLoopSplicing {

    @Test
    //使用+=进行拼接String
    public void testCase1(){
        String str = "";
        long startTime= System.currentTimeMillis();
        for (int i = 0;i< 100000;i++){
            str += i;
        }
        long endTime= System.currentTimeMillis();
        System.out.println(endTime-startTime);

    }
    //使用StringBuilder
    @Test
    public void testCase2(){
        StringBuilder stringBuilder = new StringBuilder();
        long startTime= System.currentTimeMillis();
        for (int i = 0 ; i< 100000;i++){
            stringBuilder.append(i);
        }
        long endTime= System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }

    //使用StringBuffer
    @Test
    public void testCase3(){
        StringBuffer str = new StringBuffer();
        long startTime= System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            str.append(i);
        }
        long endTime= System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}
