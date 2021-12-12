package DataStructure;

import org.junit.Test;

/**
 * 回文算法
 */
public class MoslemsTest {
    //使用快慢指针判断
    @Test
    public  void isPalindrome(){
        //定义标示作为判断
        boolean tag = true;
        int i = 12347;
        String s = String.valueOf(i);
        char[] c = s.toCharArray();
        //定义两个指针
        int front = 0;
        int after = s.length()-1;

        while (front<after){
            if (c[front] != c[after]){
                tag = false;
                break;
            }
            front++;
            after--;
        }
        System.out.println(tag);
    }
}
