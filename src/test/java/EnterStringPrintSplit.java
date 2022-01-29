import org.junit.Test;



public class EnterStringPrintSplit {
    /**
     * 判断一个字符串是否是回文字符串
     */
    @Test
    public void testPalindrome(){
        String str = "abcba";
//        StringBuilder sb = new StringBuilder(str);
        //调用内部方法反转字符串
//        String str2 = sb.reverse().toString();
        //调用自写反转方法
        String str2 = returnReverse(str);
        System.out.println(str.equals(str2)?"是回文字符串":"不是回文字符串");
    }
    public String returnReverse(String str){
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = chars.length-1; i >=0 ; i--) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }
    /**
     * 输入字符串，请按长度为8拆分每个输入字符串并进行输出；
     * 长度不是8整数倍的字符串请在后面补数字0
     */
    @Test
    public void getResoult(){
        String str ="121312121212";
        //输出8位数字，不足8位补0
        outPrint(str);
    }
    private void outPrint(String line) {
        if(line.length()<=8){
            int num = 8 - line.length();
            StringBuffer stringBuffer = new StringBuffer(line);
            for (int i = 0; i < num; i++) {
                stringBuffer.append(0);
            }
            System.out.println(stringBuffer.toString());
        }else{
            //str＝str.substring(int beginIndex，int endIndex);
            // 截取str中从beginIndex开始至endIndex结束时的字符串，并将其赋值给str;
            String substring = line.substring(0, 8);
            System.out.println(substring);
            //str＝str.substring(int beginIndex);
            // 截取掉str从首字母起长度为beginIndex的字符串，将剩余字符串赋值给str；
            System.out.println(line.substring(8));
            outPrint(line.substring(8));
        }
    }

    /**
     * 十进制转换二进制方法,再有二进制转成十进制
     */

    @Test
    public void testTwo(){
        int num = 256;
        System.out.println(getBiary(num));
        System.out.println(getInterge(getBiary(num)));
    }

    /**
     * 将二进制转换成十进制
     * @param biary:二进制
     * @return
     */
    private int getInterge(String biary) {
        int sum =0;
        //10110
        //2*43210
        for (int i = 0; i <biary.length(); i++) {
            char c = biary.charAt(i);
            int j = Integer.parseInt(c+"");
            //getTwoPower：次方函数实现
            sum+=j*getTwoPower(2,biary.length()-i-1);
        }
        return sum;
    }

    /**
     * 次方函数实现
     * @param j：底数
     * @param i：指数
     * @return
     */
    private int getTwoPower(int j, int i) {
        int a=1;
        for (int k = 0; k <i ; k++) {
           a*=j;
        }
        return a;
    }

    /**
     * 十进制转成二进制实现
     * @param num:十进制数
     * @return
     */
    private static  String getBiary(int num) {
        StringBuilder sbd = new StringBuilder();
        while (num>0){
            int x = num%2;
            sbd.append(x);
            num/=2;
        }
        sbd.reverse();
        return sbd.toString();
    }
}
