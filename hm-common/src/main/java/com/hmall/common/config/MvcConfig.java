package com.hmall.common.config;

import com.hmall.common.interceptors.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置登录拦截器
 * 注意：默认是不会生效的，因为它所在的包是com.hmall.common.config，与其它微服务的扫描包不一致，无法被扫描到，因此无法生效。
 * 基于SpringBoot的自动装配原理，我们要将其添加到resources目录下的META-INF/spring.factories文件中
 */
@Configuration
// 表示仅对包含了springMvc的核心类(DispatcherServlet)的微服务生效
@ConditionalOnClass(DispatcherServlet.class)
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInfoInterceptor());
    }
}