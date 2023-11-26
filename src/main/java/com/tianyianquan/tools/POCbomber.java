package com.tianyianquan.tools;

import com.tianyianquan.bean.ExeSysResBean;
import com.tianyianquan.entity.MyExecutor;
import com.tianyianquan.utils.ExecSysCommand;

public class POCbomber extends Tool {

    @Override
    public String toString() {
        return "POC-bomber";
    }
    public POCbomber(MyExecutor myExecutor) {
        super(myExecutor);
    }

    public String getHelp(){
        return "PocBomber 帮助文档：\n" +
                "使用:" +
                "command填入POC-bomber";
    }
    @Override
    public ExeSysResBean execute(){
        String command = getCommand().replace(this.getToolName(),"python3 /python_tools/POC-bomber/pocbomber.py");
        return ExecSysCommand.execute(command);
    }

}
