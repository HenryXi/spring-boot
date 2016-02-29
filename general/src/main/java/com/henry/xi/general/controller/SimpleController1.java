package com.henry.xi.general.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@EnableAutoConfiguration
public class SimpleController1 {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "Hello Spring Boot";
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController1.class, args);
    }
}