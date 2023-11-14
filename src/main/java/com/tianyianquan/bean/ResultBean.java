package com.tianyianquan.bean;

import java.sql.Time;
import java.sql.Timestamp;

public class ResultBean {
    private Integer id;
    // 执行结果
    private Object data;
    // 执行命令
    private String command;
    private int status;

    private Timestamp timestamp;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCommand() {
        return command;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ResultBean() {
    }

    public ResultBean(Object data, String command, int status) {
        this.data = data;
        this.command = command;
        this.status = status;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "id=" + id +
                ", data=" + data +
                ", command='" + command + '\'' +
                ", status=" + status +
                '}';
    }
}
