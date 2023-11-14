package com.tianyianquan.tools;

import com.tianyianquan.domain.MyExecutor;

public class Tool {
    private final String toolName;
    private final String command;
    private final Integer is_asynchronous;


    public Tool(MyExecutor myExecutor){
        this.command = myExecutor.getCommand();
        this.toolName = myExecutor.getTool();
        this.is_asynchronous = myExecutor.getIs_asynchronous();
    }

    public String getToolName() {
        return toolName;
    }

    public String getCommand() {
        return command;
    }

    public Integer getIs_asynchronous() {
        return is_asynchronous;
    }

}
