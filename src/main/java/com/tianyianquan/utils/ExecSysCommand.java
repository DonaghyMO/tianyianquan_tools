package com.tianyianquan.utils;

import com.tianyianquan.bean.Code;
import com.tianyianquan.bean.ExeSysResBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecSysCommand {
    public static ExeSysResBean execute(String command){
        try{
            Process process = Runtime.getRuntime().exec(command);
            //获取命令执行结果
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder lines = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.append(line).append("\n");
            }
            int exitCode = process.waitFor();

            ExeSysResBean res;

            if (exitCode == 0) {
                res = new ExeSysResBean(lines.toString(), Code.EXE_OK);
            } else {
                res = new ExeSysResBean(lines.toString(), Code.EXE_ERR);
            }
            return res;
        }catch (InterruptedException | IOException e){
            // TODO:同意加日志
            return new ExeSysResBean(e.toString(), Code.EXE_ERR);
        }


    }
}
