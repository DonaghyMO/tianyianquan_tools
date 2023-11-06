package com.tianyianquan.service.impl;

import com.tianyianquan.domain.MyExecutor;
import com.tianyianquan.pool.SyncCall;
import com.tianyianquan.service.ToolService;
import com.tianyianquan.tools.Nmap;
import com.tianyianquan.tools.Tool;

import java.util.concurrent.Callable;

public class ToolServiceImpl implements ToolService {


    @Override
    public Object[] execute(MyExecutor executor) {
        //创建线程池
        SyncCall syncCall = new SyncCall(20);
        Tool tool = new Tool(executor);


        return tool.execCommand();
    }

}
