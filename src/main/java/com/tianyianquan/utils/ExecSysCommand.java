package com.tianyianquan.utils;

import com.tianyianquan.bean.Code;
import com.tianyianquan.bean.ExeSysResBean;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecSysCommand {
    private static final Logger logger = Logger.getLogger(ExecSysCommand.class);
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
                logger.info("执行系统命令"+command+"成功！");
            } else {
                res = new ExeSysResBean(lines.toString(), Code.EXE_ERR);
                logger.error("执行系统命令"+command+"失败！"+lines);
            }
            return res;
        }catch (InterruptedException | IOException e){
            logger.error("执行系统命令"+command+"失败！"+ e);
            return new ExeSysResBean(e.toString(), Code.EXE_ERR);
        }


    }
}
