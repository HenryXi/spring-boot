package com.henryxi.post.json;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class SimpleController {
    @RequestMapping(value = "/get-json", method = RequestMethod.POST)
    public String getJson(@RequestBody UserVo userVo) {
        return userVo.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}