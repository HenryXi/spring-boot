package com.henryxi.log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableAutoConfiguration
@Configuration
@ComponentScan("com.henryxi.log4j2")
public class AppConfig extends WebMvcConfigurerAdapter {
    private static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    public static void main(String[] args) throws Exception {
        try {
            SpringApplication.run(AppConfig.class, args);
        } catch (Exception e) {
            logger.error("start error:", e);
        }
    }
}
