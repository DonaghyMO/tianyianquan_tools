package com.tianyianquan.tools;

import com.tianyianquan.domain.MyExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

public class Tool {
    private final String toolName;
    private final String command;

    private final Boolean is_asynchronous;

    public Tool(MyExecutor myExecutor){
        this.command = myExecutor.getCommand();
        this.toolName = myExecutor.getTool();
        this.is_asynchronous = myExecutor.getIs_asynchronous() == 1;
    }

    public String getToolName() {
        return toolName;
    }

    public String getCommand() {
        return command;
    }

    public Boolean getIs_asynchronous() {
        return is_asynchronous;
    }

    public Object[] execCommand(){
        try {
            // 执行命令
            Process process = Runtime.getRuntime().exec(this.getCommand());

            //返回
            Object[] ret = new Object[3];

            // 获取命令执行结果
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder lines = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.append(line).append("\n");
            }

            // 等待进程执行完成
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                ret[0] = "命令执行成功";
                ret[1] = lines.toString();
                ret[2] = true;
            } else {
                ret[0] = "命令执行失败";
                ret[1] = "";
                ret[2] = false;
            }
            return ret;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return new Object[]{"命令执行失败","",false};
        }
    }
}
