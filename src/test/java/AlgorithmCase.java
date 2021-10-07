import org.junit.Test;

import java.util.Arrays;

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
}
