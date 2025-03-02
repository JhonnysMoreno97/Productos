package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // 🔥 Deshabilita CSRF para evitar bloqueos en Postman o Frontend
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // Permitir todas las peticiones sin autenticación
        return http.build();
    }

}
