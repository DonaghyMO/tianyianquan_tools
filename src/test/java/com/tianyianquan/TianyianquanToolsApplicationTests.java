package com.tianyianquan;

import com.tianyianquan.pool.SyncCall;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootTest
class TianyianquanToolsApplicationTests {
    @Test
    void syncCall(){
        SyncCall syncCall = new SyncCall(20);
        for (int i=0;i<25;i++){
            System.out.println("这是第"+i+"次执行");
            syncCall.myExecute(this::contextLoads);
        }
    }
    @Test
    void contextLoads() {

        try {
            String command = "nmap -v baidu.com -p 8080-9000";
            // 执行命令
            Process process = Runtime.getRuntime().exec(command);

            // 获取命令执行结果
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待进程执行完成
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("命令执行成功");
            } else {
                System.out.println("命令执行失败");
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

}
