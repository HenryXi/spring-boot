package com.henryxi.exception.resolver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableAutoConfiguration
@Configuration
@ComponentScan("com.henryxi.exception.resolver")
public class AppConfig extends WebMvcConfigurerAdapter {
    @Bean
    HandlerExceptionResolver customExceptionResolver () {
        return new MyExceptionHandler();
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppConfig.class, args);
    }
}
