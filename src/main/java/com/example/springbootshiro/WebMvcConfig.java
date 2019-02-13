package com.example.springbootshiro;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * mvc 配置
 *
 * @author carzy
 * @date 2018/05/24
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 跨域配置
     *
     * @param registry CorsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(CorsConfiguration.ALL)
                .allowedMethods("GET", "DELETE", "POST", "PUT", "OPTIONS", "PATCH")
                .allowedHeaders(CorsConfiguration.ALL)
                .allowCredentials(true);
    }

}
