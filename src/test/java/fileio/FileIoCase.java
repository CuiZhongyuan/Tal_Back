package fileio;

import org.junit.Test;

import java.io.File;
import java.util.Date;

public class FileIoCase {

    @Test
    public void case1(){
        //根据目录级别 打印目录信息的一个方法
        showDir(new File("/Users/cuizhongyuan/Desktop/work/008_soft/01-idea安装包"),0);
        //递归删除一个文件下的所有文件及目录
        deleteFile(new File("/Users/cuizhongyuan/Desktop/work/aa"));
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
