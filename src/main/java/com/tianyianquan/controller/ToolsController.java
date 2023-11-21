package com.tianyianquan.controller;

import com.tianyianquan.Exceptions.ToolsNotFoundException;
import com.tianyianquan.bean.RespBean;
import com.tianyianquan.entity.MyExecutor;
import com.tianyianquan.domain.ExecuteDomain;
import com.tianyianquan.service.impl.ToolsServiceImpl;
import com.tianyianquan.tools.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/tools",produces = "application/json;charset=UTF-8")
public class ToolsController {
    @Autowired
    private ToolsServiceImpl toolService;

    @GetMapping
    public String getAll(){
        return "所有工具类使用说明：\n目前支持工具："+ Tool.my_tools;
    }

    /* nmap相关
    * 纯命令执行方式，执行结果以字符串的形式返回
    * */
    @GetMapping("/{tool_name}")
    public String help(@PathVariable(name="tool_name") String tool_name){
        StringBuilder sb = new StringBuilder(30);
        sb.append(tool_name).append("使用说明");
        return sb.toString();
    }
    @PostMapping("/{tool_name}")
    public RespBean execute(@RequestBody MyExecutor executor,@PathVariable("tool_name") String tool_name)throws ToolsNotFoundException {
        if (!Objects.equals(tool_name, executor.getTool())){
            throw new ToolsNotFoundException("工具"+tool_name+"不存在!");
        }
        ExecuteDomain res = toolService.execute(executor);
        return new RespBean(res.getData(),"",res.getStatus());
    }



}
