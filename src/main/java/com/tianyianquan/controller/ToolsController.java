package com.tianyianquan.controller;

import com.tianyianquan.domain.MyExecutor;
import com.tianyianquan.service.ToolService;
import com.tianyianquan.service.impl.ToolServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tools")
public class ToolsController {
    @GetMapping
    public String getAll(){
        return "all tools";
    }

    /* nmap相关
    * 纯命令执行方式，执行结果以字符串的形式返回
    * */
    @GetMapping("/nmap")
    public String help(){
        return "nmap使用说明";
    }
    @PostMapping("/nmap")
    public Result execute(@RequestBody MyExecutor executor){
        ToolService toolServiceImpl = new ToolServiceImpl();

        String msg = new String();
        String out = new String();
        Boolean flag = false;
        Object[] objects = toolServiceImpl.execute(executor);
        msg = (String)objects[0];
        out = (String)objects[1];
        flag = (Boolean) objects[2];

        return new Result(1,out,msg);
    }
}
