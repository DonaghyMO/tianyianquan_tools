package com.tianyianquan.controller;

import com.tianyianquan.dao.UserDao;
import com.tianyianquan.exceptions.IllegalRequestException;
import org.springframework.core.io.Resource;
import com.tianyianquan.model.UserDomain;
import com.tianyianquan.utils.AesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class AdminController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value ="/admin/logging")
    public String login(HttpServletRequest request, Model model, HttpSession session) throws Exception {
        if (request.getMethod().equals("GET")) {
            // 处理GET请求的逻辑
            return "login";
        } else if (request.getMethod().equals("POST")) {
            // 处理POST请求的逻辑
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // 处理用户名和密码，进行登录验证等操作
            UserDomain user;
            user = userDao.getByName(username);

            // 用户不存在
            if (user == null) {
                return "login";
            }

            // 验证密码
            if (password.equals(AesUtil.decrypt("1234567890123456", "1234567890123456", user.getPassword()))) {
                session.setAttribute("loginUser", username);
                return "index";
            }
            return "login";
        }
        return "login";
    }

    @RequestMapping({"/admin/index","/"})
    public String index(HttpServletRequest request, Model model, HttpSession session) {
        return "index";
    }

    private String genFileName(String fileName){
        String osName = System.getProperty("os.name").toLowerCase();
        File file;
        if(osName.equals("mac os x")){
            file = new File("/Users/donaghymo/Desktop/es",fileName);
        }
        else{
            file = new File("/tianyianquan/files",fileName);
        }

        return file.getAbsolutePath();

    }

    @GetMapping("/admin/files")
    public String getFiles(HttpServletRequest request, Model model, @RequestParam(name = "file_id") String fileId) throws FileNotFoundException {
        // 工具服务器文件统一在这管理
        String fileName = genFileName(fileId);
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException("文件不存在"+fileId);
        }
        List<Map<String,String>> fileList = new ArrayList<>();
        for(File f: file.listFiles()){
            Map<String,String> fileMap = new HashMap<>();
            fileMap.put("fileName",f.getName());
            fileMap.put("fileAddr",f.getAbsolutePath());
            fileList.add(fileMap);
        }
        model.addAttribute("fileId",fileId);
        model.addAttribute("fileList",fileList);
        return "files";
    }

    @PostMapping("/admin/files")
    public String postFile(HttpServletRequest request, @RequestParam("file") MultipartFile file, Model model) throws Exception {
        if (!file.isEmpty()) {
            try {

                LocalDateTime currentTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dirName = currentTime.format(formatter);

                // 生成文件名
                dirName = genFileName(dirName);
                Path dirObj = Paths.get(dirName);
                if(!Files.exists(dirObj)){
                    Files.createDirectories(dirObj);
                }
                // 保存文件到指定目录（这里保存在项目根目录）
                File fileToSave = new File(dirName, Objects.requireNonNull(file.getOriginalFilename()));
                if(fileToSave.exists()){
                    if(fileToSave.delete()){
                        //如果文件已经存在，则删除了在上传
                        fileToSave = new File(dirName, Objects.requireNonNull(file.getOriginalFilename()));
                    }
                }
                file.transferTo(fileToSave);
                String encodedMessage = URLEncoder.encode("上传成功，文件路径："+fileToSave.getAbsolutePath(), "UTF-8");
                return "redirect:/admin/file_suc?message="+encodedMessage;
            } catch (IOException | IllegalStateException e) {
                throw new Exception(e.toString());
            }

        } else {
            model.addAttribute("message", "文件为空");
            return "redirect:/admin/file_suc?message=文件为空";
        }
    }

    @RequestMapping("/admin/upload_file")
    public String uploadFile(){

        return "upload";
    }

    @GetMapping("/admin/file_suc")
    public String getSuc(@RequestParam String message,HttpServletRequest request, Model model, HttpSession session) {
        model.addAttribute("msg",message);
        return "upload_suc";
    }

    @GetMapping("/admin/directories")
    public String getDirectory(HttpServletRequest request, Model model){
        String filePath = genFileName("");
        File file = new File(filePath);
        List<Map<String,String>> fileList = new ArrayList<>();
        for(File f:file.listFiles()){
            if(!file.isDirectory()){
                continue;
            }
            Map<String,String> map = new HashMap<>();
            map.put("fileName",f.getName());
            fileList.add(map);
        }
        model.addAttribute("directories",fileList);

        return "directories";
    }


    private Resource loadFileAsResource(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Resource resource = new org.springframework.core.io.PathResource(path);

        if (resource.exists()) {
            return resource;
        } else {
            throw new RuntimeException("File not found: " + filePath);
        }
    }

    @GetMapping("/admin/files/download")
    public ResponseEntity<Resource> downLoadFile(HttpServletRequest request, Model model, HttpSession session,
                                                 @RequestParam(name = "file_path", defaultValue = "0") String filePath)
            throws IOException, IllegalRequestException {
        System.out.println(filePath);
//        if(!filePath.startsWith("/tianyianquan/files/")){
//            throw new IllegalRequestException("非法文件访问");
//        }
        File file = new File(filePath);
        if(!file.exists()){
            System.out.println("文件不存在");
            throw new IllegalRequestException("文件不存在");

        }
        Resource resource = loadFileAsResource(filePath);
        // 获取文件名
        String fileName = resource.getFilename();

        // 构建下载响应
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }





}