import org.junit.Test;


public class EnterStringPrintSplit {
    @Test
    public void getResoult(){
        String str ="121312121212";
//        outPrint(str);
        tt(str);

    }
    public void  tt(String str){
        char[] chars = str.toCharArray();
        int num = chars.length%8;
        StringBuffer stringBuffer = new StringBuffer(str);
        if (chars.length<8){
            for (int i=0;i<num;i++){
                stringBuffer.append("0");
            }
            System.out.println(stringBuffer);
        }else {
            System.out.println(str.substring(0,8));
            tt(str.substring(8));
        }

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

}
