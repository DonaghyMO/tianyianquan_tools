package com.tianyianquan.tools;

import com.tianyianquan.entity.MyExecutor;

public class Xray extends Tool{

    @Override
    public String toString() {
        return "xray";
    }

    public Xray(MyExecutor myExecutor) {
        super(myExecutor);
    }

    public String getHelp(){
        return "Xray 帮助文档：\n";
    }
}
