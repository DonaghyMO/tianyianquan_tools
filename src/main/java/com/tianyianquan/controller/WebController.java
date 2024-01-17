package com.tianyianquan.controller;

import com.tianyianquan.dao.UserDao;
import com.tianyianquan.domain.UserDomain;
import com.tianyianquan.utils.AesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
@RequestMapping(value="admin",produces = "application/json;charset=UTF-8")
public class WebController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/logging")
    public String login(HttpServletRequest request, Model model, HttpSession session) throws Exception {
        if (request.getMethod().equals("GET")) {
            // 处理GET请求的逻辑
            System.out.println((String) session.getAttribute("loginUser"));
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

        // 公共逻辑（可以共享GET和POST请求的处理逻辑）

        return "login"; // 重定向到仪表板页面
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model, HttpSession session) {
        return "index";
    }

    @GetMapping("/files")
    public String getPage(HttpServletRequest request, Model model, HttpSession session){
        return "upload";
    }

    @GetMapping("/file_suc")
    public String getSuc(@RequestParam String message,HttpServletRequest request, Model model, HttpSession session) {
        model.addAttribute("msg",message);
        return "upload_suc";
    }

    @RequestMapping("/file")
    public String handleFile(HttpServletRequest request, @RequestParam("file") MultipartFile file, Model model){
        if (request.getMethod().equals("POST")){
            if (!file.isEmpty()) {
                try {
                    String rootPath = "/tianyianquan/files/";
                    // 获取文件名
                    String fileName = file.getOriginalFilename();
                    // 保存文件到指定目录（这里保存在项目根目录）
                    file.transferTo(new File(rootPath,fileName));
                    String encodedMessage = URLEncoder.encode("上传成功，文件路径："+rootPath+fileName, "UTF-8");
                    return "redirect:/admin/file_suc?message="+encodedMessage;
                } catch (IOException | IllegalStateException e) {
                    model.addAttribute("message", "文件上传失败");
                    return "redirect:/admin/file_suc?message=文件上传失败";
                }

            } else {
                model.addAttribute("message", "文件为空");
                return "redirect:/admin/file_suc?message=文件为空";
            }
        }
        return "redirect:/admin/files";
    }

}