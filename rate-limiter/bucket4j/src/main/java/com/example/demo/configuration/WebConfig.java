package com.example.demo.configuration;

import com.example.demo.interceptor.RateLimitBasedAPIInterceptor;
import com.example.demo.interceptor.RateLimitBasedHostInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final RateLimitBasedHostInterceptor rateLimitBasedHostInterceptor;
    private final RateLimitBasedAPIInterceptor rateLimitBasedAPIInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(rateLimitBasedHostInterceptor)
//                .addPathPatterns("/api/**");

        registry.addInterceptor(rateLimitBasedAPIInterceptor)
                .addPathPatterns("/api/**");
    }
}
