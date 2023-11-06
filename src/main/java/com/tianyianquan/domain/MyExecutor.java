package com.tianyianquan.domain;

public class MyExecutor {
    private String tool;
    private String command;
    private int is_asynchronous;

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getIs_asynchronous() {
        return is_asynchronous;
    }

    public void setIs_asynchronous(int is_asynchronous) {
        this.is_asynchronous = is_asynchronous;
    }


    @Override
    public String toString() {
        String is_asynchronous = (this.is_asynchronous==1) ? "是":"否";
        return "工具名:"+this.tool+"\n执行命令:"+this.command+"\n是否异步调用:"+ is_asynchronous;
    }
}
