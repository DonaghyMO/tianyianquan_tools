package com.tianyianquan.tools;

import com.tianyianquan.dto.ToolExecuteParam;
import com.tianyianquan.utils.ExecSysCommand;

import java.util.Map;

public class POCbomber extends Tool {

    @Override
    public String toString() {
        return "POC-bomber";
    }
    public POCbomber(ToolExecuteParam myExecutor) {
        super(myExecutor);
    }

    public String getHelp(){
        return "PocBomber 帮助文档：\n" +
                "使用:" +
                "command填入POC-bomber";
    }
    @Override
    public Map<String, Object> execute(){
        String command = getCommand().replace(this.getToolName(),"python3 /python_tools/POC-bomber/pocbomber.py");
        return ExecSysCommand.execute(command);
    }

}
