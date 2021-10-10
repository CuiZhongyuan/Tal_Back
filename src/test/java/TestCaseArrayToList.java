import com.taltools.utils.JsonUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import sun.security.util.ArrayUtil;

import java.util.*;
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
        listToArray.add("jav");
        listToArray.add(0, "python");
        listToArray.add("php");
        listToArray.add("c#");
        String[] str = listToArray.toArray(new String[0]);
//        String[] s = new String[listToArray.size()];
//        Object[] o = listToArray.toArray();
//        for (int i = 0; i < o.length; i++) {
//            s[i] = o[i].toString();
//        }
        System.out.println(Arrays.toString(str));
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
    /**
     * 数组升序，通过Java-Arrays.sort(arr)方法完成
     */
    @Test
    public void arraySort(){
       Arrays.sort(array);
       System.out.println(Arrays.toString(array));
    }
    /**
     * 数组降序，先用sort升序，然后使用ArrayUtils.reverse(array)翻转数组;
     */
    @Test
    public void ArrayReverse(){
        //先升序
        Arrays.sort(array);
        //通过reverse 颠倒给定数组的顺序, 实现降序
        ArrayUtils.reverse(array);
        System.out.println(Arrays.toString(array));
    }
    /**
     * 集合升序
     */
    @Test
    public void ArrayListSort(){
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        Collections.sort(list);
        System.out.println(list);
    }
    /**
     * 集合降序：先升序再翻转
     */
    @Test
    public void ListReverse(){
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        Collections.sort(list,Collections.reverseOrder());
        System.out.println(list);
    }
    /**
     * 字符串切割
     */
    @Test
    public void strCase(){
        String str = "cuizhongyuan=19900725";
        String[] strArr = str.split("=");
        for (int i=0;i<strArr.length;i++){
            System.out.println(strArr[i]);
            if (strArr[i].equals("19900725")){
                String str1 = str.replace("19900725","1990 ");
                System.out.println(str1);
                System.out.println(str1.trim());
                System.out.println(str.charAt(0));
            }
        }
    }
}
