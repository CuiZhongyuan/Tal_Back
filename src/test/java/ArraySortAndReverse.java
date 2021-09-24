import org.junit.Test;

public class ArraySortAndReverse {
    @Test
    public  void test() {
        int sortArr[] = new int[]{2, 1, 9, 0, 4, 5, 3, 7, 6, 8};
        sortArr(sortArr);
        //排序升序
        sort(sortArr);
        print(sortArr);
        //排序转置
        reverse(sortArr);
        print(sortArr);
    }

    public static void sortArr(int arr[]) {
        System.out.println("原始数组：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }

    //升序排序
    public static void sort(int arr[]) {
        //外层控制总体的排序次数
        for (int i = 0; i < arr.length; i++) {
            //内层控制的排序次数
            for (int x = 0; x < arr.length - 1; x++) {
                if (arr[x] > arr[x + 1]) {
                    int t = arr[x];
                    arr[x] = arr[x + 1];
                    arr[x + 1] = t;
                }
            }
        }
        System.out.println();
    }

    public static void print(int temp[]) {
        System.out.println("排序数组：");
        for (int i = 0; i < temp.length; i++) {
            System.out.print(temp[i] + "\t");
        }
    }

    //转置排序
    public static void reverse(int arr[]) {
        System.out.println("排序转置：");
        //利用算法，在一个数组上完成所有的转置操作
        //原始数据:   1、2、3、4、5、6、7、8
        //第一次转置：8、2、3、4、5、6、7、1
        //第二次转置：8、7、3、4、5、6、2、1
        //第三次转置：8、7、6、4、5、3、2、1
        //第四次转置：8、7、6、5、4、3、2、1
        //转换次数：数组的长度除以2，它的长度是一个偶数
        //那么问题来了，如果是一个奇数，
        //这就是问题，也就是说不管是一个偶数还是一个奇数，转轩换的次数完全是一样的，所以不影响
        int len = arr.length / 2;//转换次娄
        int head = 0;//开始索引
        int tail = arr.length - 1;//尾部索引
        for (int x = 0; x < len; x++) {
            int temp = arr[head];
            arr[head] = arr[tail];
            arr[tail] = temp;
            head++;
            tail--;
        }
    }
}
