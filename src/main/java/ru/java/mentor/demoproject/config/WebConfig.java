package ru.java.mentor.demoproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.java.mentor")
public class WebConfig implements WebMvcConfigurer {

   @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/static/**").addResourceLocations("/static/");
       registry.addResourceHandler("/templates/**").addResourceLocations("/templates/");
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        System.out.println("start BCrypt");
        return new BCryptPasswordEncoder();
    }
}
