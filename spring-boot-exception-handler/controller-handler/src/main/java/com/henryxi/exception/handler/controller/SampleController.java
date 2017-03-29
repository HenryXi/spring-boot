package com.henryxi.exception.handler.controller;

import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController extends SpringBootServletInitializer {

    @RequestMapping("/exception")
    public String exception() throws Exception {
        throw new Exception("this is exception");
    }

    @RequestMapping("/custom-exception")
    public String customException() throws Exception {
        throw new CustomException("this is custom exception");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public String handleCustomException(CustomException e) {
        return e.getMessage();
    }
}
