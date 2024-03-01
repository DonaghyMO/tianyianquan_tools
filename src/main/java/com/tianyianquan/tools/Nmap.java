package com.tianyianquan.tools;

import com.tianyianquan.dto.ToolExecuteParam;

public class Nmap extends Tool {

    @Override
    public String toString() {
        return "nmap";
    }
    public Nmap(ToolExecuteParam myExecutor) {
        super(myExecutor);
    }
}
