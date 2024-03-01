package com.tianyianquan.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class ToolExecuteParam {
    private String tool;

    private Map<String,String> data;
    private int isAsynchronous;

    @Override
    public String toString() {
        String is_asynchronous = (this.isAsynchronous ==1) ? "是":"否";
        return "工具名:"+this.tool+"\n执行命令:"+"\n是否异步调用:"+ is_asynchronous;
    }
}
