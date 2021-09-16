import org.junit.Test;

import java.util.Arrays;

/**
 * 测试类
 */
public class TestCase {


    @Test
    public void intArray() {
        int[] array = new int[]{0, 5, 9, 3, 4};
        //util工具类排序
        Arrays.parallelSort(array);
        String str = Arrays.toString(array);
        System.out.println(str);
    }

}
