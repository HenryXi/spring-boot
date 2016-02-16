package com.henry.xi.spring.boot.controller;

import com.henry.xi.spring.boot.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wenchao.ren on 2014/4/26.
 */

@Controller
@EnableAutoConfiguration
@ComponentScan({"com.henry.xi.spring.boot.*"})
public class SimpleController {

    @Autowired
    private SimpleService simpleService;


    @RequestMapping(value ="/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        return simpleService.service("test");
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}