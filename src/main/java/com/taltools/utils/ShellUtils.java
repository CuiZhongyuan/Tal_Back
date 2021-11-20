package com.taltools.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ShellUtils {
    //通过Thread接收shell执行错误流和info流
    public static  List<String> execShellByThread(String script){
        Process p = null;
        List<String> errorOutList  =  new ArrayList<String>();
        List<String> infoOutList  =  new ArrayList<String>();
        String[] cmd = {"/bin/sh", "-c", script};
        try {
            p = Runtime.getRuntime().exec(cmd);
            // 创建2个线程，分别读取输入流缓冲区和错误流缓冲区
            StreamThreadImpl infoOutUtil = new StreamThreadImpl( p.getInputStream(), infoOutList);
            StreamThreadImpl errorOutUtil = new StreamThreadImpl( p.getErrorStream(), errorOutList);
            //启动线程读取缓冲区数据
            infoOutUtil.start();
            errorOutUtil.start();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            log.error("执行shell出错，命令是={}，错误信息是={}", script, e.getMessage());
        }
        log.debug("代码对比完成-->command={}，结果是-->\n{}", script, infoOutList);
        return infoOutList;
    }
}
