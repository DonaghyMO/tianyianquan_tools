package com.tianyianquan.controller.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截运营模块所有接口请求
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/tools/**");
        registry.addInterceptor(authInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/logging");
        registry.addInterceptor(authInterceptor()).addPathPatterns("/").excludePathPatterns("/admin/logging");
    }

    @Bean
    public AdminInterceptor authenticationInterceptor() {
        return new AdminInterceptor();
    }

    @Bean AuthInterceptor authInterceptor(){return new AuthInterceptor();}

}