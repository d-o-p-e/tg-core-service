package com.tg.coreservice.config;

import com.tg.coreservice.auth.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173", "https://localhost:5173", "https://dope.yanychoi.site/")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "OPTIONS")
                .exposedHeaders("Set-Cookie")
                .allowedHeaders("headers")
                .allowCredentials(true)
                .maxAge(3000);
    }
}
