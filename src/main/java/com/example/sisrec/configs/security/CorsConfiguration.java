package com.example.sisrec.configs.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Permite todos os domínios. Em produção, substitua "*" pelo domínio do seu cliente
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
