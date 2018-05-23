package com.henryxi.requestparam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@EnableAutoConfiguration
public class SamplePostController extends SpringBootServletInitializer {

    @RequestMapping("/")
    public String index() {
        return "post";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public void test(ModelMap modelMap, @RequestParam(value = "name") String name) {
        modelMap.put("result", name);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SamplePostController.class, args);
    }
}