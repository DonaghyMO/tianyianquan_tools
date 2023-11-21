package com.tianyianquan.tools;

import com.tianyianquan.bean.ExeSysResBean;
import com.tianyianquan.entity.MyExecutor;
import com.tianyianquan.utils.ExecSysCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tool {
    private final String toolName;
    private final String command;
    private final Integer is_asynchronous;

    public static final List<String> my_tools = Arrays.asList("nuclei","nmap","xray","afrog","POC-bomber","dirsearch");

    public Tool(MyExecutor myExecutor){
        this.command = myExecutor.getCommand();
        this.toolName = myExecutor.getTool();
        this.is_asynchronous = myExecutor.getIs_asynchronous();
    }

    public static Tool getTools(MyExecutor executor){
        Tool tool;
        switch (executor.getTool()){
            case "nuclei":
                tool = new Nuclei(executor);
                break;
            case "namp":
                tool = new Nmap(executor);
                break;
            case "xray":
                tool = new Xray(executor);
                break;
            case "afrog":
                tool = new Afrog(executor);
                break;
            case "POC-bomber":
                tool = new POCbomber(executor);
                break;
            case "dirsearch":
                tool = new Dirsearch(executor);
                break;
            default:
                tool = new Tool(executor);
        }
        return tool;
    }

    public ExeSysResBean execute(){
        return ExecSysCommand.execute(getCommand());
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
