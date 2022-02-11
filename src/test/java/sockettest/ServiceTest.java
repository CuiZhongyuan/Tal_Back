package sockettest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 * socket服务端编程
 */
public class ServiceTest {
    public static void main(String[] args) throws IOException {
        //输入Scanner
        Scanner scanner = new Scanner(System.in);
        //创建socket服务端实例
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端开始接受，等待用户链接");
        Socket socket =  serverSocket.accept();
        System.out.println("客服端"+socket.getInetAddress()+"已建立连接");
        //字节流，创建字节流对象后塞入处理流，用处理流的api操作
        //输入节点流
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        //输出流
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        while (true){
            //接收客户端信息
            String info = objectInputStream.readUTF();
            System.out.println("服务端接收客服端消息为："+info);
            if (info.contains("再见")){
                break;
            }
            System.out.println("=======================");
            System.out.println("服务端回复内容：");
            //服务端发送消息
            info = scanner.next();
            objectOutputStream.writeUTF(info);
            objectOutputStream.flush();
            if (info.contains("再见")){
                break;
            }
        }
        //关流，先后顺序遵循栈结构
        objectOutputStream.close();
        objectInputStream.close();
        socket.close();
        serverSocket.close();
        scanner.close();
    }
}
