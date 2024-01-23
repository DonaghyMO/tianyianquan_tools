package com.tianyianquan.common.api;

import lombok.Getter;

@Getter
public class ToolExecuteResult {
    private final Object data;
    private final String msg;
    private final long code;

    public ToolExecuteResult(Object data, String msg, long code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }
}
