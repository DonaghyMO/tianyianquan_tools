package com.tianyianquan.exceptions;

public class IllegalRequestException extends Exception{
    public IllegalRequestException(String s){
        // 非法请求
        super(s);
    }
}
