import com.alibaba.druid.sql.visitor.functions.Char;
import org.junit.Test;

import java.util.*;

public class AlgorithmCase {

    /**
     * 分治法的二分查找
     */
    @Test
    public void binaryCase(){
        //分治法必须是有序的集合或数组
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        int targetNum = 8;
        //初始定义下表
        int middle = 0;
        int low =0;
        int hight = arr.length-1;
        int isFind = 0;
        while (low<=hight){
            //取出中间位下表
            middle = (low+hight)/2;
            if (arr[middle] == targetNum){
                System.out.println("找到该数字，下标为："+middle+"----目标数字："+targetNum+"----前面数字："+arr[middle-1]);
                isFind= 1;
                break;
            }else if (arr[middle]>targetNum){
                //判断中间位置<查找数字，在左边范围
                hight= middle-1;
            }else {
                //目标数字大于中间下标数字，在右边范围
                low= middle+1;
            }
        }
        if (isFind==0){
            System.out.println("该数字无此查找数字"+targetNum);
        }

    }
    /**
     * 排序算法：冒泡排序、插入排序、归并排序、快速排序
     */
    /**
     * 冒泡排序：时间复杂度最好为刚好是从小到大排序，时间复杂度为O(n)
     * 最坏的时间复杂度为：全部逆序，时间复杂度为O(n*n)
     * 杂乱排序，平均时间复杂度为:O(n*n)
     */
    @Test
    public void sortCase1(){
        int[] arr = {9,20,4,6,-9,12,5};
        int temp;
        System.out.println("原始数组"+ Arrays.toString(arr));
        for (int i=0;i< arr.length-1;i++){
            for (int j=0;j<arr.length-i-1;j++){
                if (arr[j]>arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        System.out.println("冒泡排序后的数组："+Arrays.toString(arr));

    }
    /**
     * 插入排序：时间复杂度最好为刚好是从小到大排序，时间复杂度为O(n)
     * 最坏的时间复杂度为：全部逆序，时间复杂度为O(n*n)
     * 杂乱排序，平均时间复杂度为:O(n*n)
     */
    @Test
    public void insertCase(){
        int[] arr = {21,3,6,1,23,45,9};
        System.out.println("原始数组"+Arrays.toString(arr));
        for (int i=1;i<arr.length;i++){
            int temp = arr[i];
            int j=i-1;
            for (;j>=0;j--){
                if (arr[j]>temp){
                    arr[j+1]=arr[j];
                }else {
                    break;
                }
            }
            arr[j+1]=temp;
        }
        System.out.println("插入排序后的数组"+Arrays.toString(arr));
    }
    /**
     * 力扣：给定一个数组去重，要求：空间复杂度未O（1）
     */
    @Test
    public void arrOneCase(){
        int[] arr = {0,0,1,2,3,4,5,5,6,6,7};
        //初始化中间值和新的长度
        int temp = arr[0];
        int len =1;
        //循环去重
        for (int i=1;i<arr.length;i++){
            if (arr[i] != temp){
                arr[len] = arr[i];
                temp = arr[i];
                len++;
            }
            System.out.print(len);
        }
        for (int i=0;i<len;i++){
            System.out.print(arr[i]);
        }
        System.out.println();
        //开辟一个O（1）的空间，利用空间换时间，时间复杂度O（n）
        Set set = new HashSet();
        for (int i=0;i<arr.length;i++){
            set.add(arr[i]);
        }
        System.out.println(set);
    }
    /**
     * 题目：给定一个数组，没有重复的输出yes，反之为false,要求时间复杂度为O（n）
     */
    @Test
    public void arrCase(){
        int[] arr = {1,2,3,4,3};
        Boolean result = arrCase1(arr);
        System.out.println(result);
    }
    public Boolean arrCase1(int[] arry){
        //创建一个map空间复杂度o(n)，查询map为时间复杂度为o(1)
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<arry.length;i++){
            if (map.containsKey(arry[i])){
                return false;
            }else {
                map.put(arry[i],1);
            }
        }
        return true;
    }

    /**
     * 反转字符串
     * @return string字符串
     */
    @Test
    public void solve () {
        String str = "abcd";
        char[] chars = str.toCharArray();
        for(int i=0;i<str.length();i++){
            chars[i] = str.charAt(str.length()-i-1);
        }
        System.out.print(chars);
    }
    /**
     * 合并两个数组，再冒泡排序并排序
     */
    @Test
    public void arrSum(){
        int[] arr1 = {4,5,6,0,0,0};
        int[] arr2 = {1,2,3};
        int j = 0;
        for(int i = 3 ; i < arr1.length; i++){
            arr1[i] = arr2[j++];
        }
//        Arrays.sort(arr1);
        for (int i= 0;i<arr1.length-1;i++){
            for (int k=0;k<arr1.length-i-1;k++){
                if (arr1[k]>arr1[k+1]){
                    int temp = arr1[k];
                    arr1[k]=arr1[k+1];
                    arr1[k+1]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr1));
    }

    @Test
    public void testc(){
        String str= "dbdkcabnmo";
        char[] chars = str.toCharArray();
        for (char c : chars){
            System.out.print(c);
        }
    }
    @Test
    public void testdemo(){
        String str = "12567";
        char[] chars = str.toCharArray();
        for (char c :chars){
            int num = Integer.valueOf(String.valueOf(c));
            System.out.print(num);
        }
    }




}
