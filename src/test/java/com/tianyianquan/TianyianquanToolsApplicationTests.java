package com.tianyianquan;

import com.tianyianquan.bean.Code;
import com.tianyianquan.dao.ExecuteDao;
import com.tianyianquan.bean.ResultBean;
import com.tianyianquan.schedule.ScheduleTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TianyianquanToolsApplicationTests {
//    @Autowired
//    private ExecuteDao exeDao;
//    @Test
//    void contextLoads() {
//
//        try {
//            String command = "nmap -v baidu.com -p 8080-9000";
//            // 执行命令
//            Process process = Runtime.getRuntime().exec(command);
//
//            // 获取命令执行结果
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            // 等待进程执行完成
//            int exitCode = process.waitFor();
//            if (exitCode == 0) {
//                System.out.println("命令执行成功");
//            } else {
//                System.out.println("命令执行失败");
//            }
//        } catch (InterruptedException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testDao(){
//        ResultBean res = new ResultBean("halo","nmap -v baidu.com -p 1-2000", Code.EXE_ERR);
//        exeDao.save(res);
////        exeDao.getAll();
//    }
//
//    @Test
//    public void testGet(){
//        ArrayList<ResultBean> arr;
//        arr = (ArrayList<ResultBean>) exeDao.getAll();
//        System.out.println(arr.get(0).toString());
//
//    }
//    @Test
//    public void testLog(){
//        Path path = Paths.get("/Users/donaghymo/Desktop/test.log");
//        try {
//            // 写入文件
//            Files.write(path, "Hello, this is some text!".getBytes());
//
//            // 读取文件
//            List<String> lines = Files.readAllLines(path);
//            for (String line : lines) {
//                System.out.println(line);
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


}
