package com.tianyianquan.controller;

import com.tianyianquan.exceptions.ToolsNotFoundException;
import com.tianyianquan.common.api.ToolExecuteResult;
import com.tianyianquan.dto.ToolExecuteParam;
import com.tianyianquan.model.ExecuteDomain;
import com.tianyianquan.service.impl.ToolsServiceImpl;
import com.tianyianquan.tools.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public String getTool(@PathVariable(name="tool_name") String tool_name){
        StringBuilder sb = new StringBuilder(30);
        sb.append(tool_name).append("使用说明");
        return sb.toString();
    }
    @PostMapping("/{tool_name}")
    public ToolExecuteResult postTool(@RequestBody ToolExecuteParam executeParam, @PathVariable("tool_name") String tool_name) throws ToolsNotFoundException, IOException, InterruptedException {
        if (!Objects.equals(tool_name, executeParam.getTool())){
            throw new ToolsNotFoundException("工具"+tool_name+"不存在!");
        }
        return toolService.execute(executeParam);
    }



}
