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


        @Test
        public  void testSort()
        {
            int[] ins = {2,3,5,1,23,6,78,34};
            int[] ins2 = sort(ins,0,ins.length-1);
            for(int in: ins2){
                System.out.print(in);
            }
        }

        public  int[] sort(int[] ins ,int start,int end){

            if(start>=end){
                return ins;//这个返回值并没有影响，因为这个返回值没有使用到。
            }
            int mid = ins[start];
            int low = start;
            int hight = end;
            while(low < hight){
                while(low < hight && ins[hight]>=mid){//
                    hight -=1;
                }
                ins[low] = ins[hight];

                while(low < hight && ins[low] < mid){
                    low +=1;
                }
                ins[hight] = ins[low];
            }
            ins[low] = mid;
            sort(ins, start, low-1);
            sort(ins, low+1, end);
            return ins;
        }

        @Test
    public void bubbleSort() {
            int[] nums = {2,6,3,7,9,5};
        for (int i=1 ;i<nums.length;i++){
            for (int j=0; j<nums.length-i;j++){
                if (nums[j] > nums[j+1]){
                    int temp = nums[j];
                    nums[j] =nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }
    //选择排序，找出最小放到数组前
    @Test
    public void tt(){
        int[] arr = {5,2,7,1,8};
        int index;
        int temp;
        for (int i=0;i<arr.length;i++){
            index=i;
            for (int j=i+1;j<arr.length;j++){
                if (arr[j]<arr[index]){
                    index=j;
                }
            }
            if (index != i){
                temp=arr[i];
                arr[i]=arr[index];
                arr[index]=temp;
            }
        }
        System.out.println(Arrays.toString(arr));

    }

    @Test
    public void ttt(){
        int[] arr = {2,3,4,3,6,7,4,2,3};
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<arr.length;i++){
           if (map.containsKey(arr[i])){
               map.put(arr[i],map.get(arr[i])+1);
           }else {
               map.put(arr[i],1);
           }
        }
        List<Integer> list = new ArrayList<>();
        System.out.println(map);
    }


}
