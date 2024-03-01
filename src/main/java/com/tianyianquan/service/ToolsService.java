package com.tianyianquan.service;

import com.tianyianquan.common.api.ToolExecuteResult;
import com.tianyianquan.dto.ToolExecuteParam;

import java.io.IOException;

public interface ToolsService {
//    执行，true执行成功，false执行失败
    ToolExecuteResult execute(ToolExecuteParam executor) throws ClassNotFoundException, IOException, InterruptedException;
    int isAsynchoronous = 1;
    int notAsynchoronous = 0;
}
