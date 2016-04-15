package com.henry.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@EnableAutoConfiguration
public class SampleUploadController extends SpringBootServletInitializer {

    @RequestMapping("/")
    public String index() {
        return "upload";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleUploadController.class, args);
    }
}
