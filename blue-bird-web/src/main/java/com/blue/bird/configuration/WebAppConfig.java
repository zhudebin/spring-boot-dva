package com.blue.bird.configuration;

import com.blue.bird.configuration.interceptor.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jim on 2017/9/28.
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(LoggerInterceptor.newLoggerInterceptor()).addPathPatterns("/**");
    }
}
