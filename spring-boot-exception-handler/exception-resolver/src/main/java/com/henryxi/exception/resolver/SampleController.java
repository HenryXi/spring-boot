package com.henryxi.exception.resolver;

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
}
