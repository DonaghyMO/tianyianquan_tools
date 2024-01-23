package com.tianyianquan.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ExecuteDomain {
    private Integer id;
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
