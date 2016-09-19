package com.henryxi.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {
    @Autowired
    private SimpleService simpleService;

    @RequestMapping("/spring-unit-test")
    @ResponseBody
    public String testProperties() {
        return simpleService.serviceMethod();
    }
}
