package com.tianyianquan.controller;

import com.tianyianquan.bean.RespBean;
import com.tianyianquan.domain.MyExecutor;
import com.tianyianquan.bean.ResultBean;
import com.tianyianquan.service.impl.ToolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tools")
public class ToolsController {
    @Autowired
    private ToolServiceImpl toolService;

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
    public RespBean execute(@RequestBody MyExecutor executor){
        ResultBean res = toolService.execute(executor);
        return new RespBean(res.getData(),"",res.getStatus());
    }

}
