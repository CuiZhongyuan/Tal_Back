package DataStructure;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AlgorithmTest {

    /**
     * 无重复字符的最长子串，
     * eg:输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */
    @Test
    public void getLength(){
        String str="abvabd";
        char[] c = str.toCharArray();
        Map map = new HashMap();
        for (char c1 : c){
            map.put(c1,1);
        }
       int l =  map.keySet().size();
        System.out.println(l);
    }

    /**
     * 跳台阶算法
     * eg:一层台阶：方法1种
     * 二层台阶：1->1；直接跳2层
     * 3层台阶：1->1;1->2-1;直接跳3层
     * ...
     * n层台阶：sum=f(n-1)+f(n-2)
     */
    @Test
    public void case01(){
        int num = 4;
        int sum = getSum(num);
        System.out.println(sum);
    }
    public int getSum(int num){
        if (num == 1){
            return 1;
        }else if (num == 2){
            return 2;
        }else {
            return getSum(num-1)+getSum(num-2);
        }
    }
}
