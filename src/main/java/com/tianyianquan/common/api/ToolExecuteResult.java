package com.tianyianquan.common.api;

import lombok.Getter;

@Getter
public class ToolExecuteResult {
    private final String data;
    private final String msg;
    private final long code;
    private final String status;

    public ToolExecuteResult(String data, String msg, long code,String aStatus) {
        this.data = data;
        this.msg = msg;
        this.code = code;
        this.status = aStatus;
    }
}
