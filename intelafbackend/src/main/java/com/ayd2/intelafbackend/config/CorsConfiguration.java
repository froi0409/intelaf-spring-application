package com.ayd2.intelafbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Permitir solicitudes desde cualquier origen
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir los métodos HTTP especificados
                .allowedHeaders("*"); // Permitir todas las cabeceras
    }
}
