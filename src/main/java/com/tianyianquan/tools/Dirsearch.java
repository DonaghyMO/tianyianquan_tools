package com.tianyianquan.tools;

import com.tianyianquan.dto.ToolExecuteParam;
import com.tianyianquan.utils.ExecSysCommand;

import java.util.Map;

public class Dirsearch extends Tool{
    @Override
    public String toString() {
        return "dirsearch";
    }

    public Dirsearch(ToolExecuteParam myExecutor) {
        super(myExecutor);
    }

    public String getHelp(){
        return "dirsearch 帮助文档：\n";
    }

    @Override
    public Map<String,Object> execute(){
        String command = getCommand().replace(this.getToolName(),"python3 /python_tools/dirsearch/dirsearch.py");
        return ExecSysCommand.execute(command);
    }
}
