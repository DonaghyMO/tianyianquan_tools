package com.tianyianquan.controller.interceptor;

import com.tianyianquan.utils.AesEncodeUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;


public class AdminInterceptor implements HandlerInterceptor {
    private final Logger logger = Logger.getLogger(getClass());
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出签名
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        String sign = httpServletRequest.getHeader("Sign");
        String decKey = AesEncodeUtil.decrypt(sign);
        final String message = "接口鉴权失败，请在前端系统添加鉴权值";
        if (Objects.nonNull(decKey)) {
            return true;
        } else {
            try (PrintWriter out = httpServletResponse.getWriter()) {
                String responseJson = "{\"message\":\"" + message + "\",\"success\": false,\"code\": 403}";
                logger.error(responseJson);
            } catch (IOException e) {
                logger.error("鉴权错误:"+message);
                throw new RuntimeException("系统错误：接口鉴权异常");
            }

            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

}

