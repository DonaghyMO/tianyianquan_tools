package com.tianyianquan.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class ScheduleTask {
    @Scheduled(fixedRate = 20000)//每20秒向soar同步
    public void testTask(){
        Path path = Paths.get("/Users/donaghymo/Desktop/test.log");
        try {
            // 写入文件
            Files.write(path, "Hello, this is some text!".getBytes());

            // 读取文件
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
