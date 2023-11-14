package com.tianyianquan.service.impl;

import com.tianyianquan.bean.Code;
import com.tianyianquan.bean.ResultBean;
import com.tianyianquan.dao.ExecuteDao;
import com.tianyianquan.domain.AsyncFlag;
import com.tianyianquan.domain.MyExecutor;
import com.tianyianquan.pool.SyncCall;
import com.tianyianquan.bean.ExeSysResBean;
import com.tianyianquan.utils.ExecSysCommand;
import com.tianyianquan.service.ToolService;
import com.tianyianquan.tools.Tool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
@Service
public class ToolServiceImpl implements ToolService {
    @Autowired
    private ExecuteDao executeDao;

    @Override
    public ResultBean execute(MyExecutor executor) {
        Tool tool = new Tool(executor);
        // 异步执行
        if (executor.getIs_asynchronous() == AsyncFlag.isAsynchoronous) {
            //创建线程池
            try {
                ExecutorService syncCall = SyncCall.getCaller();
                syncCall.execute(() -> {
                    ExeSysResBean res;
                    res = ExecSysCommand.execute(tool.getCommand());
                    ResultBean resultBean = new ResultBean(res.getOutput(),tool.getCommand(),res.getStatus());
                    System.out.println("ttttt");
                    executeDao.save(resultBean);
                });
                return new ResultBean("异步执行，请查看日志查看执行结果", executor.getCommand(), Code.SYNC_EXE_OK);
            } catch (NullPointerException e){
                Logger LOGGER = LogManager.getLogger(ToolServiceImpl.class);
                LOGGER.info("hallo\n\n\n");

                return new ResultBean("异步执行失败", executor.getCommand(), Code.SYNC_EXE_ERR);
            }
        }
        // 同步执行，soar前端会阻塞
        else{
            ExeSysResBean res = ExecSysCommand.execute(executor.getCommand());
            String data = res.getOutput();
            return new ResultBean(data,executor.getCommand(),res.getStatus());
        }
    }



}
