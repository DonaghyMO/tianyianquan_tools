package com.tianyianquan.service;

import com.tianyianquan.domain.MyExecutor;
import com.tianyianquan.bean.ResultBean;

public interface ToolService {
//    执行，true执行成功，false执行失败
    ResultBean execute(MyExecutor executor);
}
