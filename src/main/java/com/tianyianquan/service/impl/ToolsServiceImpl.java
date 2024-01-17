package com.tianyianquan.service.impl;

import com.tianyianquan.exceptions.ToolsNotFoundException;
import com.tianyianquan.bean.Code;
import com.tianyianquan.domain.ExecuteDomain;
import com.tianyianquan.dao.ExecuteDao;
import com.tianyianquan.entity.MyExecutor;
import com.tianyianquan.pool.SyncCall;
import com.tianyianquan.bean.ExeSysResBean;
import com.tianyianquan.tools.*;
import com.tianyianquan.service.ToolsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
@Service
public class ToolsServiceImpl implements ToolsService {
    @Autowired
    private ExecuteDao executeDao;
    org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ExecuteDomain execute(MyExecutor executor) throws ToolsNotFoundException {

        // 静态方法，通过工具类创建工具对象
        Tool tool = Tool.getTools(executor);

        // 异步执行
        if (executor.getIs_asynchronous() == this.isAsynchoronous) {
            //创建线程池
            try {
                logger.info("异步执行开始:"+executor.getCommand());
                ExecutorService syncCall = SyncCall.getCaller();
                syncCall.execute(() -> {
                    ExeSysResBean res;
                    res = tool.execute();
                    ExecuteDomain executeDomain = new ExecuteDomain(res.getOutput(),tool.getCommand(),res.getStatus());
                    executeDao.save(executeDomain);
                    //TODO:这里加上向soar发消息，发送执行结果
                });
                return new ExecuteDomain("异步执行，请查看日志查看执行结果", executor.getCommand(), Code.SYNC_EXE_OK);
            } catch (NullPointerException e){
                Logger LOGGER = LogManager.getLogger(ToolsServiceImpl.class);
                LOGGER.info("hallo\n\n\n");
                logger.error("异步执行失败"+executor.getCommand());
                return new ExecuteDomain("异步执行失败", executor.getCommand(), Code.SYNC_EXE_ERR);
            }
        }
        // 同步执行，soar前端会阻塞
        else{
            logger.info("同步执行开始:"+executor.getCommand());
            ExeSysResBean res = tool.execute();
            String data = res.getOutput();
            if (res.getStatus()!= Code.EXE_OK){
                logger.error("同步执行失败:"+executor.getCommand()+res.getOutput());
            }
            return new ExecuteDomain(data,executor.getCommand(),res.getStatus());
        }
    }



}
