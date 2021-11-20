package com.taltools.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 线程接收shell Console结果
 */
public class StreamThreadImpl implements Runnable {
    // 设置读取的字符编码
    private final String CHARACTER = "utf8";
    private List<String> list;
    private InputStream inputStream;

    public StreamThreadImpl(InputStream inputStream, List<String> list) {
        this.inputStream = inputStream;
        this.list = list;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.setDaemon(true);//将其设置为守护线程
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream, CHARACTER));
            String line = null;
            //File tmpLogFile = new File(gitDiffPath  + "/" + projectName + buildVersion + ".txt");
            /*if (!tmpLogFile.exists()){
                tmpLogFile.createNewFile();
            }*/
            //FileWriter fileWriter = new FileWriter(tmpLogFile,true);
            while ((line = br.readLine()) != null) {
                if (line != null) {
                    //fileWriter.append(line + "\n");
                    list.add(line);
                }
            }
            list.add("+++ b");
            //fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //释放资源
                inputStream.close();
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
