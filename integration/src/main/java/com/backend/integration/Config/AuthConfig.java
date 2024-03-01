package com.backend.integration.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.backend.integration.Security.SecurityFilter;

@Configuration
@EnableWebSecurity
public class AuthConfig {

    // Autowires the SecurityFilter bean
    @Autowired
    SecurityFilter securityFilter;

    // Configures and returns a BCryptPasswordEncoder bean for password encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configures the security filter chain for HTTP security
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                // Permits specific HTTP methods for certain endpoints without authentication
                .requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/auth/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/auth/**").permitAll()
                // Requires authentication for any other requests
                .anyRequest().authenticated())
            // Adds the custom SecurityFilter before the default UsernamePasswordAuthenticationFilter
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    // Configures and returns an AuthenticationManager bean
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
