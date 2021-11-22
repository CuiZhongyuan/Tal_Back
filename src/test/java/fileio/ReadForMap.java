package fileio;

import org.apache.commons.collections.map.LinkedMap;

import java.io.*;
import java.util.*;

/**
 * 联系读文件，并去重取出出现次数为2的第二个网址
 */
public class ReadForMap {
    public static void main(String[] args) {
        String path = "/Users/cuizhongyuan/Desktop/work/010_project/vueSpringboot/Tal_Back/src/test/java/fileio/file.txt";
        List list = readLine(path);
        //有序的链表map,是基于HashMap和双向链表来实现的
        LinkedMap map = new LinkedMap();
        list.forEach(l ->{
            if (map.containsKey(l)){
                map.put(l,Integer.valueOf(map.get(l).toString())+1);
            }else {
                map.put(l,1);
            }
        });
        Collection collection = map.values();
        Object[] arrays = collection.toArray();
        int[] a =  new int[4];
        for (int i=0;i<arrays.length;i++){
            a[i] = (int) arrays[i];
        }
        for (int i=0;i<a.length-1;i++){
            for (int j=0;j<a.length-i-1;j++){
                int temp;
                if (a[j]<a[j+1]){
                    temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
        //打印数组用Arrays.toString()
        System.out.println(Arrays.toString(a));
        System.out.println(map);
        System.out.println(collection);

    }
    public static List readLine(String path){
        //获取文件
        File file = new File(path);
        //读取文件后放入StringBuffer里，StringBuffer为线程安全
        StringBuffer stringBuffer = new StringBuffer();
        //读文件方法
        BufferedReader bufferedReader = null;
        List list = new ArrayList();
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String str;
            while ((str=bufferedReader.readLine()) != null){
                //!Objects.equals("",str)方法内存在判断
                //!Objects.equals("",str)&&!str.contains("#"):判断字符串地址是否相等建议用此方法
                if (!"".equals(str)&&!str.contains("#")){
                    stringBuffer.append(str);
                    list.add(str);
                }
            }
            //关流
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
