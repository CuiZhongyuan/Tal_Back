package gitdiff.diff1;

import java.util.Random;

public class A {
    public static void main(String[] args){
        int getNum = randomNumber(10,20);
        if (getNum==30){
            System.out.println("中间数="+getNum);
        }else {
            System.out.println("此次没有找到="+getNum);
        }

    }
    public static int randomNumber(int minNum,int maxNum){
        Random rand = new Random();
        int randomNum = rand.nextInt(maxNum);
        randomNum = randomNum%(maxNum-minNum+1)+minNum;
        return randomNum;
    }
}
