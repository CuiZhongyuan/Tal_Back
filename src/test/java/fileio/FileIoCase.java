package fileio;

import org.junit.Test;

import java.io.*;
import java.util.Date;

public class FileIoCase {

    //整体复制目录及子目录文件
    @Test
    public void testCopy() throws IOException {
        copyDirectory(new File("/Users/cuizhongyuan/Desktop/work/test"),
                new File("/Users/cuizhongyuan/Desktop/work/test01"));
    }
    /**
     *
     * @param oldDir:原目录路径
     * @param newDir：目标路径
     * @throws IOException
     */
    //目录复制
    public void copyDirectory(File oldDir,File newDir) throws IOException {
        if (!newDir.exists()){
            newDir.mkdirs();
        }
        File[] files = oldDir.listFiles();
        for (File file:files){
            //通过父目录和子目录，获取当前子目录
            File nextFile = new File(newDir,file.getName());
            if (file.isFile()){
                //如果是文件，走调用单个文件复制
                iOPictureCopy(file,nextFile);
            }else {
                //如果是子目录，走递归复制
                copyDirectory(file,nextFile);
            }
        }

    }
    /**
     * 通过字节流复制多媒体文件（图片或者视频）
     */
    //复制单个文件
    public void iOPictureCopy(File oldFile,File newFile) throws IOException {
        //字节流输入流
        //节点流
        InputStream is = new FileInputStream(oldFile);
        //处理流
        BufferedInputStream bis = new BufferedInputStream(is);
        //字节流输出流
        //节点流
        OutputStream os = new FileOutputStream(newFile);
        //处理流,通常使用处理流的api
        BufferedOutputStream bos  = new BufferedOutputStream(os);
        //文件的读取和输出
        //准备缓冲区
        byte[] buffer = new byte[1024];
        //重点：读取数据进入缓冲区，返回数据最多存到哪个索引
        int index = bis.read(buffer);
        //字节流末尾读完时索引为负数
        while (index>=0){
            //写入缓冲区
            bos.write(buffer,0,index);
            //如果1024缓冲区装不下，循环再次读取写入
            index = bis.read(buffer);
        }
        //关流
        bis.close();
        bos.close();
    }
    /**
     * 通过字符流完成文本的复制
     */
    @Test
    public void iOtestCopy() throws IOException{
        //读取原文本
        Reader reader = new FileReader("/Users/cuizhongyuan/Desktop/work/access.log");
        BufferedReader br = new BufferedReader(reader);
        //写入新文本，FileWriter文件名会自动创建
        Writer writer = new FileWriter("/Users/cuizhongyuan/Desktop/work/test/access.log");
        BufferedWriter bw = new BufferedWriter(writer);
        String line = br.readLine();
        while (line !=null){
            System.out.println(line);
            bw.write(line);
            bw.newLine();
            //读取新的一行内容赋值给line
            line = br.readLine();
        }
        System.out.println("copy完成");
        //关流
        br.close();
        bw.close();
    }

    @Test
    public void case1(){
        //根据目录级别 打印目录信息的一个方法
        showDir(new File("/Users/cuizhongyuan/Desktop/work/008_soft/01-idea安装包"),0);
        //递归删除一个文件下的所有文件及目录
//        deleteFile(new File("/Users/cuizhongyuan/Desktop/work/aa"));
    }
    /**
     * 递归删除一个不确定文件下的文件和目录
     */
    public void deleteFile(File dir){
       File[] files = dir.listFiles();
       for (File file : files){
           if (file.isFile()){
               //删除文件
               file.delete();
           }else {
               //递归删除子文件
               deleteFile(file);
           }
           //删除当前目录本身
           dir.delete();
       }

    }
    /**
     * 根据目录级别 打印目录信息的一个方法
     * @param dir 要查看的目录
     * @param level 当前目录的级别
     */
    public  void showDir(File dir, int level){
        File[] files = dir.listFiles();
        for (File file:files){
            for (int i=1;i<=level;i++){
                System.out.print("\t");
            }
            System.out.print(new Date(file.lastModified()).toLocaleString()+"\t");
            if(file.isFile()){
                System.out.println(file.length()+" name:"+file.getName());
            }else{
                System.out.println("<DIR> name:"+file.getName());
                showDir(file,level+1);
            }
        }
    }

}
