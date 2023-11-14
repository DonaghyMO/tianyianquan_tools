package com.tianyianquan.bean;

public class RespBean {
    private Object data;
    private String msg;
    private int code;

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public RespBean(Object data, String msg, int code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }
}
