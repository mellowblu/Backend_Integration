package com.backend.integration.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    // Configures resource handlers to serve static resources
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/static/**")  // Maps requests for resources under "/static"
            .addResourceLocations("classpath:/static");  // Specifies the location of the static resources in the classpath
    }
}
