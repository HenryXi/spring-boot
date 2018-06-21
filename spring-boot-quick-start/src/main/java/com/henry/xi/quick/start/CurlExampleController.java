package com.henry.xi.quick.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@EnableAutoConfiguration
public class CurlExampleController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam(value = "name") String name) {
        return "Hello " + name + "\n";
    }

    @RequestMapping(path = "/post-json", method = RequestMethod.POST)
    @ResponseBody
    public String postXml(@RequestBody User user) throws IOException {
        return user.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(CurlExampleController.class, args);
    }
}