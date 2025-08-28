package com.ecommerce.be_ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Authorize requests
                .authorizeHttpRequests(auth -> auth
                        // Allow Swagger endpoints without authentication
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-ui/index.html"
                        ).permitAll()
                        // Allow email endpoint without authentication
                        .requestMatchers("/email/orderConfirmation").permitAll()
                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                // Disable CSRF for email endpoint and Swagger
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-ui/index.html",
                                "/email/orderConfirmation"
                        )
                );

        return http.build();
    }
}