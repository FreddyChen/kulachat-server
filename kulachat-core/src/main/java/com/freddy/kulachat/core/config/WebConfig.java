package com.freddy.kulachat.core.config;

import com.freddy.kulachat.core.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author FreddyChen
 * @name Web配置，添加拦截器等
 * @date 2020/09/24 14:52
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public RequestInterceptor initRequestInterceptor() {
        return new RequestInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(initRequestInterceptor()).addPathPatterns("/**")/*.excludePathPatterns("/user/login")*/;
    }
}
