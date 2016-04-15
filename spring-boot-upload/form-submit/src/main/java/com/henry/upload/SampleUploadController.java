package com.henry.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringBufferInputStream;
import java.io.StringReader;

@Controller
@EnableAutoConfiguration
public class SampleUploadController extends SpringBootServletInitializer {

    @RequestMapping("/")
    public String index() {
        return "upload";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile filename) {
        CommonsMultipartFile cf = (CommonsMultipartFile) filename;
        ByteArrayInputStream stream = new StringReader(file.getBytes());
        String myString = IOUtils.toString(stream, "UTF-8");
        return "success";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleUploadController.class, args);
    }
}
