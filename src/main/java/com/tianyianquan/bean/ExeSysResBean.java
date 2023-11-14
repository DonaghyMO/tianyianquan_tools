package com.tianyianquan.bean;

public class ExeSysResBean {
    private String output;
    private int status;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ExeSysResBean(String output, int status) {
        this.output = output;
        this.status = status;
    }


}
