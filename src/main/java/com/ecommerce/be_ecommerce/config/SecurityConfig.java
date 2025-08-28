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
                .authorizeHttpRequests(auth -> auth
                        // allow Swagger endpoints without auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-ui/index.html",
                                "/email/**"
                        ).permitAll()
                        // allow your email endpoint without auth (for testing)
                        .requestMatchers("/email/orderConfirmation").permitAll()
                        .anyRequest().authenticated()
                )
                // disable CSRF for Swagger and email endpoint
                .csrf(csrf -> csrf.ignoringRequestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-ui/index.html",
                        "/email/orderConfirmation"
                ));

        return http.build();
    }
}