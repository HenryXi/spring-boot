package com.henryxi.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@Controller
public class SimpleController {

    @Autowired
    private MyService myService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(HttpServletRequest request) {
        myService.hello();
        return "finish";
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}