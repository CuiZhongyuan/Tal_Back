package sockettest;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
/**
 * socket客服端编程
 */
public class ClientTest {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        //客服端
        Socket socket = new Socket("127.0.0.1",9999);
        //发送信息==>输出流节点流和处理流，大部分用处理流的api方法
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        //接收信息==>
        InputStream is = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
        System.out.println("客服端开始发送消息：");
        while (true){
            //客服端发送消息
            String info = scanner.next();
            oos.writeUTF(info);
            oos.flush();
            System.out.println("===============");
            System.out.println("客服端向服务端发送消息为：");
            if (info.contains("再见")){
                break;
            }
            //客服端接收消息
            info = ois.readUTF();
            System.out.println("客服端接收服务端消息为："+info);
            if (info.contains("再见")){
                break;
            }
        }
        ois.close();
        oos.close();
        socket.close();
        scanner.close();
    }
}
