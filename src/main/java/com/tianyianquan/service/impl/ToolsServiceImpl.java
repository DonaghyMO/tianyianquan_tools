package com.tianyianquan.service.impl;

import com.tianyianquan.common.api.ToolExecuteResult;
import com.tianyianquan.exceptions.ToolsNotFoundException;
import com.tianyianquan.common.api.ResultCode;
import com.tianyianquan.model.ExecuteDomain;
import com.tianyianquan.dao.ExecuteDao;
import com.tianyianquan.dto.ToolExecuteParam;
import com.tianyianquan.pool.SyncCall;
import com.tianyianquan.tools.*;
import com.tianyianquan.service.ToolsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutorService;
@Service
public class ToolsServiceImpl implements ToolsService {
    @Autowired
    private ExecuteDao executeDao;
    org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ToolExecuteResult execute(ToolExecuteParam executor) throws ToolsNotFoundException {

        // 静态方法，通过工具类创建工具对象
        Tool tool = Tool.getTools(executor);

        // 异步执行
        if (executor.getIsAsynchronous() == this.isAsynchoronous) {
            //创建线程池
            try {
                logger.info("异步执行开始:"+executor.getCommand());
                ExecutorService syncCall = SyncCall.getCaller();
                syncCall.execute(() -> {
                    Map<String, Object> res;
                    res = tool.execute();
                    ExecuteDomain executeDomain = new ExecuteDomain(res.get("output"),tool.getCommand(),(Long)res.get("code"));
                    executeDao.save(executeDomain);
                    //TODO:这里加上向soar发消息，发送执行结果
                });
                return new ToolExecuteResult("异步执行，请查看日志查看执行结果",
                        "异步执行，请查看日志查看执行结果",
                        ResultCode.SYNC_EXE_OK.getCode());
            } catch (NullPointerException e){
                Logger LOGGER = LogManager.getLogger(ToolsServiceImpl.class);
                LOGGER.info("hallo\n\n\n");
                logger.error("异步执行失败"+executor.getCommand());
                return new ToolExecuteResult("异步执行失败",
                        executor.getCommand(),
                        ResultCode.SYNC_EXE_ERR.getCode());
            }
        }
        // 同步执行，soar前端会阻塞
        else{
            logger.info("同步执行开始:"+executor.getCommand());
            Map<String, Object> res = tool.execute();
            String data = (String)res.get("output");
            if ((Long)res.get("code")!= ResultCode.EXE_OK.getCode()){
                logger.error("同步执行失败:"+executor.getCommand()+(String)res.get("output"));
            }
            return new ToolExecuteResult(data,
                    executor.getCommand(),
                    (Long)res.get("code"));
        }
    }



}
