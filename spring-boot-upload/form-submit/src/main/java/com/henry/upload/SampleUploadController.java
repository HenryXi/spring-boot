package com.henry.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@EnableAutoConfiguration
public class SampleUploadController extends SpringBootServletInitializer {

    @RequestMapping("/")
    public String index() {
        return "upload";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            String content = new String(file.getBytes());
            return "file name:" + file.getOriginalFilename() + "<br> content:" + content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "file name:" + file.getOriginalFilename() +"<br> read file content error.";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleUploadController.class, args);
    }
}
