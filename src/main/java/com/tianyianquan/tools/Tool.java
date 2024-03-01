package com.tianyianquan.tools;

import com.tianyianquan.dto.ToolExecuteParam;
import com.tianyianquan.utils.ExecSysCommand;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Tool {
    private final String toolName;
    private final String command;
    private final Integer isAsynchronous;

    private Map<String,String> data;

    public static final List<String> my_tools = Arrays.asList("nuclei","nmap","xray","afrog","POC-bomber","dirsearch");

    public Tool(ToolExecuteParam myExecutor){
        this.command = myExecutor.getCommand();
        this.toolName = myExecutor.getTool();
        this.isAsynchronous = myExecutor.getIsAsynchronous();
        this.data = myExecutor.getData();
    }
    public static Tool getTools(ToolExecuteParam executor){
        Tool tool;
        switch (executor.getTool()){
            case "nuclei":
                tool = new Nuclei(executor);
                break;
            case "namp":
                tool = new Nmap(executor);
                break;
            case "xray":
                tool = new Xray(executor);
                break;
            case "afrog":
                tool = new Afrog(executor);
                break;
            case "POC-bomber":
                tool = new POCbomber(executor);
                break;
            case "dirsearch":
                tool = new Dirsearch(executor);
                break;
            case "httpx":
                tool = new Httpx(executor);
                break;
            default:
                tool = new Tool(executor);
                break;
        }
        return tool;
    }

    public String getWorkDir(String onlyId) throws IOException {
        // 创建今天的文件夹
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dirName = currentTime.format(formatter);
        File toDayDir = new File("/tianyianquan/files",dirName);
        Path dirPath = Paths.get(toDayDir.getAbsolutePath());
        if(!Files.exists(dirPath)){
            Files.createDirectories(dirPath);
        }
        // 用onlyId创建文件夹
        File onlyIdDir = new File(dirPath.toFile().getAbsolutePath(),onlyId);
        Path onlyIdDirPath = Paths.get(onlyIdDir.getAbsolutePath());
        if(Files.exists(onlyIdDirPath)){
            Files.delete(onlyIdDirPath);
        }
        Files.createDirectories(onlyIdDirPath);

        return onlyIdDirPath.toFile().getAbsolutePath();
    }

    public Map<String, Object> execute() throws IOException {
        return ExecSysCommand.execute(getCommand());
    }

    public String getToolName() {
        return toolName;
    }

    public String getCommand() {
        return command;
    }

    public Integer getIsAsynchronous() {
        return isAsynchronous;
    }

    public Map<String, String> getData() {
        return data;
    }
}
