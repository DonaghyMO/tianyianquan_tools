package com.tianyianquan.utils;

import com.tianyianquan.common.api.ResultCode;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ExecSysCommand {
    private static final Logger logger = Logger.getLogger(ExecSysCommand.class);
    public static Map<String,Object> execute(String command){
        Map<String, Object> res = new HashMap<>();
        try{
            Process process = Runtime.getRuntime().exec(new String[]{"bash","-l","-c","whoami"});
            int exitCode = process.waitFor();
            //获取命令执行结果
            BufferedReader reader = new BufferedReader(exitCode==0?new InputStreamReader(process.getInputStream()):new InputStreamReader(process.getErrorStream()));
            StringBuilder lines = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.append(line).append("\n");
            }



            if (exitCode == 0) {
                res.put("output",lines.toString());
                res.put("code",ResultCode.EXE_OK.getCode());
                logger.info("执行系统命令"+command+"成功！");
            } else {
                res.put("output",lines.toString());
                res.put("code",ResultCode.EXE_ERR.getCode());
                logger.error("执行系统命令"+command+"失败！"+lines);
            }
            return res;
        }catch (InterruptedException | IOException e){
            logger.error("执行系统命令"+command+"失败！"+ e);
            res.put("output",e.toString());
            res.put("code",ResultCode.EXE_ERR.getCode());
            return res;
        }


    }
}
