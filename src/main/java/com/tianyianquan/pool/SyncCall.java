package com.tianyianquan.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SyncCall {
    private int poolSize;
    private ExecutorService executor;
    public ExecutorService getExecutor() {
        return executor;
    }
    public SyncCall(int poolSize) {
        this.poolSize = poolSize;
        this.executor = Executors.newFixedThreadPool(this.poolSize);
    }

    //执行，适用于没有返回值
    public void myExecute(Runnable runnable){
        this.executor.execute(runnable);
    }

    //执行，使用于有返回值
    public String mySubmit(Callable callable){
        return this.executor.submit(callable).toString();
    }


}
