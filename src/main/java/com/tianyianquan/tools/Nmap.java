package com.tianyianquan.tools;

import com.tianyianquan.entity.MyExecutor;

public class Nmap extends Tool {

    @Override
    public String toString() {
        return "nmap";
    }
    public Nmap(MyExecutor myExecutor) {
        super(myExecutor);
    }
}
