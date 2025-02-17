package dev.fmhj97.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* 
 * Configuración de CORS para permitir peticiones desde el frontend
 * en http://localhost:4200
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        /*
         * Se permite el acceso a los métodos GET, POST, PUT, DELETE y OPTIONS
         * desde el origen http://localhost:4200
         * Se permiten todas las cabeceras y se permiten credenciales (cookies)
         */
        return new WebMvcConfigurer() {
            @SuppressWarnings("null")
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permitir acceso a todos los endpoints
                        .allowedOrigins("http://localhost:4200") // Permitir acceso desde http://localhost:4200
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permitir los métodos GET, POST,
                                                                                   // PUT, DELETE y OPTIONS
                        .allowedHeaders("*") // Permitir todas las cabeceras
                        .allowCredentials(true); // Permitir credenciales (cookies)
            }
        };
    }
}