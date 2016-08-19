package com.henryxi.resources;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@EnableAutoConfiguration
public class ResourcesController {
    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping(value = "/get-resources", method = RequestMethod.GET)
    public String getResources() throws IOException {
        String content = IOUtils.toString(resourceLoader.getResource("classpath:test.txt").getInputStream());
        return "the content of resources:" + content;
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourcesController.class, args);
    }
}