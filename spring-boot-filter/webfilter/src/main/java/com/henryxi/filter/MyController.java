package com.henryxi.filter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {


    @ResponseBody
    @RequestMapping("/**")
    public String anyRequest(){
        return "this is available page.";
    }
}
