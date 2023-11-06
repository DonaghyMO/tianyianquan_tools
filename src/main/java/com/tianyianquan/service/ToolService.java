package com.tianyianquan.service;

import com.tianyianquan.domain.MyExecutor;

public interface ToolService {
//    执行，true执行成功，false执行失败
    public Object[] execute(MyExecutor executor);
}
