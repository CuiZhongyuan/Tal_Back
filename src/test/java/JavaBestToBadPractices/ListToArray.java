package JavaBestToBadPractices;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author czy
 *  集合转数组：toArray()
 *  有些场景下（比如入参要求）需要将集合（比如List）转为数组类型，利用集合的toArray方法应该最为方便的，
 *  对于toArray()无参方法其返回的是Object[]，强制转其他类型数组会ClassCastException。
 *  推荐使用带参数的toArray(T[])，不过使用上需要注意
 */
public class ListToArray {
    @Test
    //list强转数组抛异常-ClassCastException
    public void arrayError(){
        List<String> listToArray = new ArrayList<>();
        listToArray.add("jav");
        listToArray.add(0, "python");
        listToArray.add("php");
        listToArray.add("c#");
        String[] str = (String[]) listToArray.toArray();
        System.out.println(str);
    }

    @Test
    //list转数组，使用object对象接受再循环遍历转出
    public void arrayObject(){
        List<String> listToArray = new ArrayList<>();
        listToArray.add("jav");
        listToArray.add(0, "python");
        listToArray.add("php");
        listToArray.add("c#");
        String[] s = new String[listToArray.size()];
        Object[] o = listToArray.toArray();
        for (int i = 0; i < o.length; i++) {
            s[i] = o[i].toString();
        }
        System.out.println(Arrays.toString(s));
    }

    @Test
    //list转数组-最优化写法
    public void OptimizeCode(){
        List<String> listToArray = new ArrayList<>();
        listToArray.add("jav");
        listToArray.add(0, "python");
        listToArray.add("php");
        listToArray.add("c#");
        String[] str = listToArray.toArray(new String[0]);
        System.out.println(Arrays.toString(str));
    }


}
