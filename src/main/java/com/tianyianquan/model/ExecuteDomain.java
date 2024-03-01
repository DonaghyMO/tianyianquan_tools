package com.tianyianquan.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ExecuteDomain {
    private Integer id;
    private String onlyId;
    private Integer isAsync;
    // 执行结果
    private Object data;
    // 执行命令
    private String command;
    private long status;

    private Timestamp timestamp;

    public ExecuteDomain(Object data, String command,long status) {
        this.data = data;
        this.command = command;
        this.status = status;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
    public ExecuteDomain(String command,long status,String aOnlyId) {
        this.command = command;
        this.status = status;
        this.onlyId = aOnlyId;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public ExecuteDomain(Integer id, Object data, String command,Integer isAsync, long status,Timestamp timestamp, String onlyId) {
        this.id = id;
        this.onlyId = onlyId;
        this.data = data;
        this.isAsync = isAsync;
        this.command = command;
        this.status = status;
        this.timestamp = timestamp;
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
