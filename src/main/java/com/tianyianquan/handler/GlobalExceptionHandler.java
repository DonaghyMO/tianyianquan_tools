package com.tianyianquan.handler;

import com.tianyianquan.exceptions.ToolsNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //这个类是集中处理所有 @Controller 发生的错误
public class GlobalExceptionHandler {
    private final Logger logger = Logger.getLogger(getClass());
    @ResponseBody //对象写出为json
//    @ExceptionHandler(Exception.class)
    public String handleException(Exception e){

        return "Ohho~~~统一处理，原因："+e.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(ToolsNotFoundException.class)
    public String handleToolsNotFound(ToolsNotFoundException e){
        logger.error(e.getMessage());
        return "出错："+e.getMessage();
    }
}
