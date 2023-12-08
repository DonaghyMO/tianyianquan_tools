package com.tianyianquan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="logs",produces = "application/json;charset=UTF-8")
public class WebController {

    @RequestMapping("/login")
    public String home(HttpServletRequest request, Model model){
        if (request.getMethod().equals("GET")) {
            // 处理GET请求的逻辑
            System.out.println("GET request received");
        } else if (request.getMethod().equals("POST")) {
            // 处理POST请求的逻辑
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // 处理用户名和密码，进行登录验证等操作
            System.out.println("Received username: " + username);
            System.out.println("Received password: " + password);


            // 在这里添加登录验证逻辑
        }

        // 公共逻辑（可以共享GET和POST请求的处理逻辑）

        return "index"; // 重定向到仪表板页面
    }
}
