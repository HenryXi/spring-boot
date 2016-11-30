package com.henryxi.interceptor;

import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController extends SpringBootServletInitializer {

    @RequestMapping("/intercept")
    @ResponseBody
    public String intercept() {
        System.out.println("this is controller, request path is intercept");
        return "hello spring boot interceptor, request path is intercept";
    }

    @RequestMapping("/not-intercept")
    @ResponseBody
    public String notIntercept() {
        System.out.println("this is controller, request path is not intercept");
        return "hello spring boot interceptor, request path is not intercept";
    }
}
