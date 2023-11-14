package com.tianyianquan.pool;

import java.util.concurrent.*;

public class SyncCall {
    //饿汉单例
    // 线程池默认大小
    private static final int poolSize=20;
    //TODO:核心线程数，和最大线程数
    private static ExecutorService caller = Executors.newFixedThreadPool(poolSize);

    private SyncCall(){
    }

    public static ExecutorService getCaller(){
        return caller;
    }

}
