package com.tianyianquan.service;

import com.tianyianquan.entity.MyExecutor;
import com.tianyianquan.domain.ExecuteDomain;

public interface ToolsService {
//    执行，true执行成功，false执行失败
    ExecuteDomain execute(MyExecutor executor) throws ClassNotFoundException;
    final int isAsynchoronous = 1;
    final int notAsynchoronous = 0;
}
