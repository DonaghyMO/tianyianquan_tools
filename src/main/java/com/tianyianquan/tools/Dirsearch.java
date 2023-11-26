package com.tianyianquan.tools;

import com.tianyianquan.bean.ExeSysResBean;
import com.tianyianquan.entity.MyExecutor;
import com.tianyianquan.utils.ExecSysCommand;

public class Dirsearch extends Tool{
    @Override
    public String toString() {
        return "dirsearch";
    }

    public Dirsearch(MyExecutor myExecutor) {
        super(myExecutor);
    }

    public String getHelp(){
        return "dirsearch 帮助文档：\n";
    }

    @Override
    public ExeSysResBean execute(){
        String command = getCommand().replace(this.getToolName(),"python3 /python_tools/dirsearch/dirsearch.py");
        return ExecSysCommand.execute(command);
    }
}
