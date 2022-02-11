package reflecttest;

import com.taltools.entity.UserEntity;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReflectCase {
    /***
     * 获取一个class类的字节码对象
     */
    @Test
    public void testRelect() throws ClassNotFoundException {
        //通过类路径获取
        //一般通过配置文件获取反射
        Class userClass1 = Class.forName("com.taltools.entity.UserEntity");
        //1、通过类名获取
        //该方式不仅仅是为了动态，更多的是获取一个类的全部内容
       Class userClass2 = UserEntity.class;
       //2、通过对象获取类字节码
        //一般是多态的时候使用反射的
        //当子类声明父类对象时，通过对象获取的class字节码对象是子类的字节码对象
        UserEntity u = new UserEntity();
        Object o = new UserEntity();
        Class userClass3 = u.getClass();
        Class userClass4 = o.getClass();
        //任何一个类的字节码对象在内存上只有一个
        System.out.println(userClass1==userClass2&&userClass3==userClass2&&userClass4==userClass3);
    }
    /**
     * 读取class字节码内容
     */
    @Test
    public void getReader() throws IOException {
       Class  userClass = UserEntity.class;
       InputStream rs = userClass.getResourceAsStream("/application.yml");
       InputStreamReader isr = new InputStreamReader(rs);
       BufferedReader br = new BufferedReader(isr);
       String s =br.readLine();
    }
    /**
     * 通过反射字节码对象，实例化对象
     */
    @Test
    public void objectTest() throws Exception {
        UserEntity u = UserEntity.class.newInstance();
        u.setName("test");
        System.out.println(u);
    }

}
