package sockettest;

import org.junit.Test;

import java.util.Arrays;

public class UdpTest {
    @Test
    public void test(){
        //二进制字节流
        String str = "测试";
        byte[] bytes = str.getBytes();
        System.out.println(Arrays.toString(bytes));
        //二进制字节转String
        String getStr = new String(bytes,0,bytes.length);
        System.out.println(getStr);
    }
}
