package com.tianyianquan.tools;

import com.tianyianquan.dto.ToolExecuteParam;

public class Xray extends Tool{

    @Override
    public String toString() {
        return "xray";
    }

    public Xray(ToolExecuteParam myExecutor) {
        super(myExecutor);
    }

    public String getHelp(){
        return "Xray 帮助文档：\n";
    }
}
