package com.tianyianquan.tools;

import com.tianyianquan.common.api.ResultCode;
import com.tianyianquan.dto.ToolExecuteParam;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Httpx extends Tool{
    private static final Logger logger = Logger.getLogger(Httpx.class);
    public Httpx(ToolExecuteParam executor) {
        super(executor);
    }

    @Override
    public String toString() {
        return "httpx";
    }
    @Override
    public Map<String, Object> execute() throws IOException{
        Map<String, Object> res = new HashMap<>();
        if(getData().get("action").equals("is_http_alive")){

            try{
                String workDir = this.getWorkDir(getData().get("only_id"));
                // 没有上传的话就在工作目录创建输入文件
                String command;
                if(!getData().get("is_uploaded").equals("1")){
                    File fileToSave= new File(workDir, "input_file.txt");
                    String content = getData().get("input_file");
                    try{
                        FileWriter fw = new FileWriter(fileToSave.getAbsolutePath());
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(content);
                        bw.close();
                    }catch (IOException e){
                        logger.error("is_http_alive创建输入文件出错");
                    }
                    command = "bash /tianyianquan/scripts/is-http-alive.sh "+workDir+" "+ fileToSave.getAbsolutePath();;
                }
                else{
                    command = "bash /tianyianquan/scripts/is-http-alive.sh "+workDir+" "+getData().get("input_file");
                }


                // 拼接执行命令

                logger.info("生成了shell命令:"+command);
                res.put("command",command);
                res.put("onlyId",getData().get("only_id"));
                res.put("code", ResultCode.NOT_EXE.getCode());

            }catch (IOException e){
                logger.error(e.toString());
            }




            return res;
        }
        else{
            return super.execute();
        }
    }
}
