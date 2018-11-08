package com.henryxi.mavenplugin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class TestController {
    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public String get() {
        return "This is Get response";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestController.class, args);
    }
}
