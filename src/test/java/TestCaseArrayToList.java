import com.taltools.utils.JsonUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 测试类
 */
public class TestCaseArrayToList {
    int[] array = new int[]{0, 5, 9, 3, 4};
    String[] stringArray = { "a", "b", "c", "d", "e" };
    /**
     * 数组常用方法
     */
    @Test
    public void array01() {
        //util工具类排序
        Arrays.parallelSort(array);
        //整型数组转化成字符串
        String str = Arrays.toString(array);
        System.out.println(str);
    }
    @Test
    //组创建ArrayList
    public void arry02(){
        //整型数组转成list集合，元素为字符
        ArrayList<String> arrayToList = new ArrayList<>(Arrays.asList(stringArray));
        System.out.println(arrayToList);
    }
    @Test
    //list转数组
    public void array03(){
        List<String> listToArray = new ArrayList<>();
        listToArray.add("hello");
        listToArray.add(0, "world");
        listToArray.add("MLDN");
        listToArray.add("www.mldn.cn");

        String[] s = new String[listToArray.size()];
        Object[] o = listToArray.toArray();
        for (int i = 0; i < o.length; i++) {
            s[i] = o[i].toString();
        }
        System.out.println(Arrays.toString(s));
    }

    /**
     * list集合常用方法
     *
     */
    @Test
    public void listCase(){
        //整型数组转list集合，需要装箱下
        List<Integer> arrayToList = Arrays.stream(array).boxed().collect(Collectors.toList());
        List<String> stringList = new ArrayList<>(Arrays.asList(stringArray));
        int getArraySize = arrayToList.size();
        System.out.println(arrayToList.get(2));
        System.out.println(getArraySize);
        //list数组转字符串
        String strList= String.join(",", stringList);
        System.out.println(strList);
    }

}
