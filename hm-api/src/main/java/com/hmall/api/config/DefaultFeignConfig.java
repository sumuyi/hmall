package com.hmall.api.config;

import com.hmall.common.utils.UserContext;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfig {
    @Bean
    public RequestInterceptor userInfoRequestInterceptor(){
        return template -> {
            // 获取登录用户
            Long userId = UserContext.getUser();
            if(userId == null) {
                // 如果为空则直接跳过
                return;
            }
            // 如果不为空则放入请求头中，传递给下游微服务
            template.header("user-info", userId.toString());
        };
    }
}
