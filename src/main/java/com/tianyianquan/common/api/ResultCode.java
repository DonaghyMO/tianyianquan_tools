package com.tianyianquan.common.api;

import lombok.Getter;

@Getter
public enum ResultCode{
    SUCCESS(200, "操作成功"),

    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    SYNC_EXE_OK(20060, "异步执行成功"),
    SYNC_EXE_ERR(20061, "异步执行失败"),
    EXE_OK(20050,"同步执行成功"),
    EXE_ERR(20051,"同步执行失败"),
    SAVE_OK(20011,"保存成功"),
    NOT_EXE(20052,"还未执行"),
    IN_EXE(20053,"正在执行中");




//    public static final Integer DELETE_OK = 20021;
//    public static final Integer UPDATE_OK = 20031;
//    public static final Integer GET_OK = 20041;
//    public static  final Integer EXE_OK = 20050;
//    public static final Integer SYNC_EXE_OK = 20060;
//
//    public static final Integer SAVE_ERR = 20010;
//    public static final Integer DELETE_ERR = 20020;
//    public static final Integer UPDATE_ERR = 20030;
//    public static final Integer GET_ERR = 20040;
//    public static final Integer EXE_ERR = 20051;
//    public static final Integer SYNC_EXE_ERR = 20061;

    private final long code;
    private final String message;
    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

}
